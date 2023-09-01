package cn.elvea.platform.auth.config;

import cn.elvea.platform.auth.security.authentication.*;
import cn.elvea.platform.auth.security.token.CustomTokenCustomizer;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;

import static cn.elvea.platform.commons.core.constants.SecurityConstants.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            OAuth2AuthorizationService authorizationService,
            OAuth2TokenGenerator<?> tokenGenerator
    ) throws Exception {
        log.info("creating authorizationServerSecurityFilterChain...");

        PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
        SocialAuthenticationProvider socialAuthenticationProvider = new SocialAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        authorizationServerConfigurer.tokenEndpoint(tokenEndpoint -> tokenEndpoint
                .accessTokenRequestConverters(authenticationConverters -> authenticationConverters.addAll(List.of(
                        new PasswordAuthenticationConverter(),
                        new SmsAuthenticationConverter(),
                        new SocialAuthenticationConverter()
                )))
                .authenticationProviders(authenticationProviders -> authenticationProviders.addAll(List.of(
                        passwordAuthenticationProvider,
                        socialAuthenticationProvider,
                        smsAuthenticationProvider
                )))
        );

        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.securityMatcher(endpointsMatcher)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .apply(authorizationServerConfigurer);
        return http.build();
    }

    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource, CustomTokenCustomizer tokenCustomizer) {
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
