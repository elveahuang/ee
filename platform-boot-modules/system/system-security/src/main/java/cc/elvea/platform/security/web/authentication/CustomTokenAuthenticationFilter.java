package cc.elvea.platform.security.web.authentication;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;

/**
 * @author elvea
 */
@Slf4j
public class CustomTokenAuthenticationFilter extends BearerTokenAuthenticationFilter {

    public CustomTokenAuthenticationFilter(AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver) {
        super(authenticationManagerResolver);
    }

}
