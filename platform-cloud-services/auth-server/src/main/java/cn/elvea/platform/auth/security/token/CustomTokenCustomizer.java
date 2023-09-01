package cn.elvea.platform.auth.security.token;

import cn.elvea.platform.commons.core.constants.SecurityConstants;
import cn.elvea.platform.system.core.api.UserApi;
import cn.elvea.platform.system.core.model.dto.UserLoginDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Component
public class CustomTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    private final UserApi userApi;

    @Override
    public void customize(JwtEncodingContext context) {
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
            JwtClaimsSet.Builder claims = context.getClaims();

            Authentication principal = context.getPrincipal();

            UserLoginDto user = userApi.findByUsername(principal.getName()).getData();
            if (user != null) {
                claims.claim(SecurityConstants.JWT_KEY_UID, user.getId());
                claims.claim(SecurityConstants.JWT_KEY_USERNAME, user.getUsername());
                claims.claim(SecurityConstants.JWT_KEY_NAME, user.getDisplayName());
            }

            // 合并用户权限和应用权限存进凭证
            Set<String> authorities = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
            Set<String> authorizedScopes = context.getAuthorizedScopes();
            authorities.addAll(authorizedScopes);

            claims.claim(SecurityConstants.JWT_KEY_AUTHORITIES, authorities);
        }
    }

}
