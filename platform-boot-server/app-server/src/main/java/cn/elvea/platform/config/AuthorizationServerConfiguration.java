package cn.elvea.platform.config;

import cn.elvea.platform.security.authentication.*;
import cn.elvea.platform.security.token.CustomTokenCustomizer;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.function.Function;

import static cn.elvea.platform.commons.core.constants.SecurityConstants.*;
import static cn.elvea.platform.commons.core.storage.domain.FileParameter.withDefault;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AllArgsConstructor
public class AuthorizationServerConfiguration {

    private final JwtDecoder jwtDecoder;

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            OAuth2AuthorizationService authorizationService,
            OAuth2TokenGenerator<?> tokenGenerator
    ) throws Exception {
        log.info("Creating authorizationServerSecurityFilterChain for App Server...");

        PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);
        SocialAuthenticationProvider socialAuthenticationProvider = new SocialAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator);

        Function<OidcUserInfoAuthenticationContext, OidcUserInfo> userInfoMapper = (context) -> {
            OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
            JwtAuthenticationToken principal = (JwtAuthenticationToken) authentication.getPrincipal();

            return new OidcUserInfo(principal.getToken().getClaims());
        };

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        authorizationServerConfigurer.oidc(oidc -> oidc.userInfoEndpoint((userInfo) -> userInfo
                .userInfoMapper(userInfoMapper)
        ));
        authorizationServerConfigurer.tokenEndpoint(tokenEndpoint -> tokenEndpoint
                .accessTokenRequestConverters(authenticationConverters -> authenticationConverters.addAll(List.of(
                        new SmsAuthenticationConverter(),
                        new PasswordAuthenticationConverter(),
                        new SocialAuthenticationConverter()
                )))
                .authenticationProviders(authenticationProviders -> authenticationProviders.addAll(List.of(
                        socialAuthenticationProvider,
                        passwordAuthenticationProvider,
                        smsAuthenticationProvider
                )))
        );

        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.securityMatcher(endpointsMatcher)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
                .cors(cors -> withDefault())
                .oauth2ResourceServer((resourceServerConfigurer) -> resourceServerConfigurer.jwt(jwtConfigurer -> {
                    jwtConfigurer.decoder(jwtDecoder);
                    jwtConfigurer.jwtAuthenticationConverter(this.jwtAuthenticationConverter);
                }))
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
