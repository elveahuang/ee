package cc.elvea.platform.auth.config;

import cc.elvea.platform.auth.security.authentication.*;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.List;
import java.util.function.Function;

import static cc.elvea.platform.commons.constants.SecurityConstants.*;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class AuthorizationServerConfiguration {

    private final JwtDecoder jwtDecoder;

    private final JWKSource<SecurityContext> jwkSource;

    private final OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            OAuth2AuthorizationService authorizationService,
            OAuth2TokenGenerator<?> tokenGenerator
    ) throws Exception {
        log.info("creating authorizationServerSecurityFilterChain...");

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer();

        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .with(authorizationServerConfigurer, Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());

        PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
        SocialAuthenticationProvider socialAuthenticationProvider = new SocialAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);

        Function<OidcUserInfoAuthenticationContext, OidcUserInfo> userInfoMapper = (context) -> {
            OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
            JwtAuthenticationToken principal = (JwtAuthenticationToken) authentication.getPrincipal();
            return new OidcUserInfo(principal.getToken().getClaims());
        };

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(oidc ->
                oidc.userInfoEndpoint((userInfo) -> userInfo.userInfoMapper(userInfoMapper))
        ).tokenEndpoint(tokenEndpoint ->
                tokenEndpoint.accessTokenRequestConverters(authenticationConverters ->
                        authenticationConverters.addAll(List.of(
                                new SmsAuthenticationConverter(),
                                new PasswordAuthenticationConverter(),
                                new SocialAuthenticationConverter()
                        ))
                ).authenticationProviders(authenticationProviders -> authenticationProviders.addAll(List.of(
                        socialAuthenticationProvider,
                        passwordAuthenticationProvider,
                        smsAuthenticationProvider
                )))
        );

        http.oauth2ResourceServer((rsc) -> rsc.jwt(configurer -> {
                    configurer.decoder(jwtDecoder);
                }))
                .cors(Customizer.withDefaults())
                .exceptionHandling(e -> {
                    e.authenticationEntryPoint(authenticationEntryPoint);
                    e.accessDeniedHandler(accessDeniedHandler);
                });

        return http.build();
    }

    @Bean
    public OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator() {
        JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
        jwtGenerator.setJwtCustomizer(tokenCustomizer);
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .authorizationEndpoint(OAUTH_AUTHORIZATION_ENDPOINT)
                .deviceAuthorizationEndpoint(OAUTH_DEVICE_AUTHORIZATION_ENDPOINT)
                .deviceVerificationEndpoint(OAUTH_DEVICE_VERIFICATION_ENDPOINT)
                .tokenEndpoint(OAUTH_TOKEN_ENDPOINT)
                .tokenIntrospectionEndpoint(OAUTH_TOKEN_INTROSPECTION_ENDPOINT)
                .tokenRevocationEndpoint(OAUTH_TOKEN_REVOCATION_ENDPOINT)
                .jwkSetEndpoint(OAUTH_JWK_SET_ENDPOINT)
                .oidcUserInfoEndpoint(OAUTH_OIDC_USER_INFO_ENDPOINT)
                .oidcLogoutEndpoint(OAUTH_OIDC_LOGOUT_ENDPOINT)
                .oidcClientRegistrationEndpoint(OAUTH_OIDC_CLIENT_REGISTRATION_ENDPOINT)
                .build();
    }

}
