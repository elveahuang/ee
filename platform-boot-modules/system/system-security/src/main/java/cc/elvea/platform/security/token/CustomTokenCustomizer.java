package cc.elvea.platform.security.token;

import cc.elvea.platform.commons.constants.SecurityConstants;
import cc.elvea.platform.commons.core.security.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            if (context.getPrincipal().getPrincipal() instanceof User user) {
                context.getClaims().claims(claims -> {
                    claims.put(SecurityConstants.JWT_KEY_UID, user.getId());
                    claims.put(SecurityConstants.JWT_KEY_USERNAME, user.getName());
                    claims.put(SecurityConstants.JWT_KEY_AUTHORITIES, user.getGrantedAuthority());
                });
            }
        } else if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
            if (context.getPrincipal().getPrincipal() instanceof User user) {
                context.getClaims().claims(claims -> {
                    claims.put(SecurityConstants.JWT_KEY_UID, user.getId());
                });
            }
        }
    }

}
