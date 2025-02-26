package cc.elvea.platform.security.configuration;

import cc.elvea.platform.security.authentication.*;
import cc.elvea.platform.security.web.authentication.CaptchaAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static cc.elvea.platform.commons.constants.SecurityConstants.*;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfiguration {

    private final JwtDecoder jwtDecoder;

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    private final CaptchaAuthenticationFilter captchaAuthenticationFilter;

    private final AuthenticationManager authenticationManager;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AccessDeniedHandler accessDeniedHandler;

    private final OAuth2AuthorizationService authorizationService;

    private final OAuth2TokenGenerator<?> tokenGenerator;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("Creating authorizationServerSecurityFilterChain...");

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer();

        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .with(authorizationServerConfigurer, Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());

        Function<OidcUserInfoAuthenticationContext, OidcUserInfo> userInfoMapper = (context) -> {
            OidcUserInfoAuthenticationToken authentication = context.getAuthentication();
            JwtAuthenticationToken principal = (JwtAuthenticationToken) authentication.getPrincipal();
            return new OidcUserInfo(principal.getToken().getClaims());
        };

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(oidc ->
                oidc.userInfoEndpoint((userInfo) -> userInfo.userInfoMapper(userInfoMapper))
        ).tokenEndpoint(tokenEndpoint -> {
            tokenEndpoint.accessTokenRequestConverters(authenticationConverters -> authenticationConverters.addAll(authenticationConverterList()));
            tokenEndpoint.accessTokenResponseHandler(authenticationSuccessHandler);
            tokenEndpoint.errorResponseHandler(authenticationFailureHandler);
        });

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .addFilterAfter(this.captchaAuthenticationFilter, CorsFilter.class)
                .exceptionHandling(e -> {
                    e.authenticationEntryPoint(authenticationEntryPoint);
                    e.accessDeniedHandler(accessDeniedHandler);
                }).oauth2ResourceServer((rsc) -> rsc.jwt(jc -> {
                    jc.decoder(jwtDecoder);
                    jc.jwtAuthenticationConverter(this.jwtAuthenticationConverter);
                }));

        // 添加自定义授权模式实现
        this.addAuthenticationProvider(http);

        return http.build();
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

    private List<AuthenticationConverter> authenticationConverterList() {
        return Arrays.asList(
                new SmsAuthenticationConverter(),
                new PasswordAuthenticationConverter(),
                new SocialAuthenticationConverter()
        );
    }

    private void addAuthenticationProvider(HttpSecurity http) {
        // 密码模式
        PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider(
                this.authenticationManager, this.authorizationService, this.tokenGenerator);
        http.authenticationProvider(passwordAuthenticationProvider);

        // 验证码模式
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider(
                this.authenticationManager, this.authorizationService, this.tokenGenerator);
        http.authenticationProvider(smsAuthenticationProvider);

        // 社区模式
        SocialAuthenticationProvider socialAuthenticationProvider = new SocialAuthenticationProvider(
                this.authenticationManager, this.authorizationService, this.tokenGenerator);
        http.authenticationProvider(socialAuthenticationProvider);
    }

}
