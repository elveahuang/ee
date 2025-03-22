package cc.elvea.platform.auth.security.authentication;

import cc.elvea.platform.commons.core.security.CustomAuthorizationGrantType;
import cc.elvea.platform.commons.core.security.CustomParameterNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

/**
 * @author elvea
 */
@Slf4j
public class SmsAuthenticationProvider extends AbstractAuthenticationProvider {

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
    private final AuthenticationManager authenticationManager;
    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;

    public SmsAuthenticationProvider(AuthenticationManager authenticationManager,
                                     OAuth2AuthorizationService authorizationService,
                                     OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        this.authenticationManager = authenticationManager;
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;

        OAuth2ClientAuthenticationToken clientPrincipal = getAuthenticatedClient(smsAuthenticationToken);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();

        // 检查当前客户端是否包含密码默模式
        if (registeredClient != null && !registeredClient.getAuthorizationGrantTypes().contains(CustomAuthorizationGrantType.SMS)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        // 验证获取用户登录凭证
        Authentication smsAuthentication = getSmsAuthentication(smsAuthenticationToken);

        // 验证授权范围
        Set<String> authorizedScopes = validateScopes(smsAuthenticationToken.getScopes(), registeredClient);

        //
        OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(smsAuthentication.getName())
                .authorizationGrantType(CustomAuthorizationGrantType.SMS)
                .authorizedScopes(authorizedScopes)
                .attribute(Principal.class.getName(), CustomAuthorizationGrantType.PASSWORD);

        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(smsAuthenticationToken)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(authorizedScopes)
                .authorizationGrantType(CustomAuthorizationGrantType.SMS)
                .authorizationGrant(smsAuthenticationToken);

        // ----- Access token -----
        log.info("create access token by grant type {}.", CustomAuthorizationGrantType.SMS);
        OAuth2AccessToken accessToken = createOAuth2AccessToken(tokenContextBuilder, authorizationBuilder,
                this.tokenGenerator, ERROR_URI);

        // ----- Refresh token -----
        log.info("create refresh token by grant type {}.", CustomAuthorizationGrantType.SMS);
        OAuth2RefreshToken refreshToken = creatOAuth2RefreshToken(tokenContextBuilder, authorizationBuilder,
                this.tokenGenerator, ERROR_URI, clientPrincipal, registeredClient);

        // ----- ID token -----
        log.info("create id token by grant type {}.", CustomAuthorizationGrantType.SMS);
        OidcIdToken idToken = createOidcIdToken(tokenContextBuilder, authorizationBuilder,
                this.tokenGenerator, ERROR_URI, smsAuthenticationToken.getScopes());

        //
        OAuth2Authorization authorization = authorizationBuilder.build();
        this.authorizationService.save(authorization);
        log.trace("Saved authorization for grant type {}.", CustomAuthorizationGrantType.SMS);

        Map<String, Object> additionalParameters = idTokenAdditionalParameters(idToken);
        //
        return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken, refreshToken, additionalParameters);
    }

    private Authentication getSmsAuthentication(SmsAuthenticationToken smsAuthenticationToken) {
        Map<String, Object> additionalParameters = smsAuthenticationToken.getAdditionalParameters();
        String mobile = (String) additionalParameters.get(CustomParameterNames.MOBILE);
        String code = (String) additionalParameters.get(CustomParameterNames.CODE);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(mobile, code);
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
