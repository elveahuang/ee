package cc.elvea.platform.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

/**
 * @author elvea
 */
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    private final CustomUserDetailsService userDetailsService;

    private final CustomAccountDetailsService accountDetailsService;

    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(CustomUserDetailsService userDetailsService, CustomAccountDetailsService accountDetailsService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.accountDetailsService = accountDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(CustomAuthenticationToken.class, authentication, () -> this.messages.getMessage("CustomAuthenticationProvider.onlySupports", "Only CustomAuthenticationToken is supported"));

        String username = determineUsername(authentication);

        UserDetails user;
        try {
            user = retrieveUser(username, (CustomAuthenticationToken) authentication);
        } catch (UsernameNotFoundException ex) {
            log.info("Failed to find user '{}'", username);
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");

        additionalAuthenticationChecks(user, (CustomAuthenticationToken) authentication);
        return createSuccessAuthentication(user, authentication, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (CustomAuthenticationToken.class.isAssignableFrom(authentication));
    }

    protected void additionalAuthenticationChecks(UserDetails userDetails, CustomAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.info("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            log.info("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    protected UserDetails retrieveUser(String username, CustomAuthenticationToken authentication) throws AuthenticationException {
        try {
            UserDetails loadedUser = this.getUserDetailsService(authentication.getType()).loadUserByUsername(username);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        } catch (UsernameNotFoundException | InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    private UserDetailsService getUserDetailsService(String type) {
        if ("account".equalsIgnoreCase(type)) {
            return this.accountDetailsService;
        }
        return this.userDetailsService;
    }

    private String determineUsername(Authentication authentication) {
        return (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
    }

    private Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        UsernamePasswordAuthenticationToken result = CustomAuthenticationToken.authenticated(principal, authentication.getCredentials(), this.authoritiesMapper.mapAuthorities(user.getAuthorities()));
        result.setDetails(authentication.getDetails());
        log.info("Authenticated user");
        return result;
    }

}
