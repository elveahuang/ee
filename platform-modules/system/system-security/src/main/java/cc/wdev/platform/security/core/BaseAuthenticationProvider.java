package cc.wdev.platform.security.core;

import cc.wdev.platform.security.core.service.BaseUserDetailsService;
import cc.wdev.platform.security.core.service.CustomAccountDetailsService;
import cc.wdev.platform.security.core.service.CustomUserDetailsService;
import cc.wdev.platform.system.commons.enums.EntityTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

/**
 * @author elvea
 */
@Slf4j
public abstract class BaseAuthenticationProvider implements AuthenticationProvider {

    protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    protected final GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    protected final CustomUserDetailsService userDetailsService;

    protected final CustomAccountDetailsService accountDetailsService;

    public BaseAuthenticationProvider(
        CustomUserDetailsService userDetailsService,
        CustomAccountDetailsService accountDetailsService
    ) {
        this.userDetailsService = userDetailsService;
        this.accountDetailsService = accountDetailsService;
    }

    protected BaseUserDetailsService getUserDetailsService(String type) {
        if (EntityTypeEnum.ACCOUNT.getCode().equalsIgnoreCase(type)) {
            return this.accountDetailsService;
        }
        return this.userDetailsService;
    }

}
