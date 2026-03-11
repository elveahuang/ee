package cc.wdev.platform.security.configuration;

import cc.wdev.platform.commons.utils.jwt.JwtConfig;
import cc.wdev.platform.security.core.OtpAuthenticationProvider;
import cc.wdev.platform.security.core.PasswordAuthenticationProvider;
import cc.wdev.platform.security.core.SocialAuthenticationProvider;
import cc.wdev.platform.security.core.service.CustomAccountDetailsService;
import cc.wdev.platform.security.core.service.CustomUserDetailsService;
import cc.wdev.platform.security.core.web.CustomAuthenticationFailureHandler;
import cc.wdev.platform.security.core.web.authentication.CaptchaAuthenticationFilter;
import cc.wdev.platform.system.core.api.CaptchaApi;
import cc.wdev.platform.system.core.api.ConfigApi;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class CommonSecurityConfiguration {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordAuthenticationProvider customAuthenticationProvider(
        @Qualifier("userDetailsService") CustomUserDetailsService userDetailsService,
        @Qualifier("accountDetailsService") CustomAccountDetailsService accountDetailsService
    ) {
        return new PasswordAuthenticationProvider(userDetailsService, accountDetailsService);
    }

    @Bean
    public SocialAuthenticationProvider customWechatSocialAuthenticationProvider(
        @Qualifier("userDetailsService") CustomUserDetailsService userDetailsService,
        @Qualifier("accountDetailsService") CustomAccountDetailsService accountDetailsService
    ) {
        return new SocialAuthenticationProvider(userDetailsService, accountDetailsService);
    }

    @Bean
    public OtpAuthenticationProvider customSmsAuthenticationProvider(
        @Qualifier("userDetailsService") CustomUserDetailsService userDetailsService,
        @Qualifier("accountDetailsService") CustomAccountDetailsService accountDetailsService) {
        return new OtpAuthenticationProvider(userDetailsService, accountDetailsService);
    }

    @Bean("userAuthenticationManager")
    public AuthenticationManager userAuthenticationManager(
        PasswordAuthenticationProvider passwordAuthenticationProvider,
        SocialAuthenticationProvider socialAuthenticationProvider,
        OtpAuthenticationProvider otpAuthenticationProvider
    ) {
        return new ProviderManager(List.of(
            passwordAuthenticationProvider,
            socialAuthenticationProvider,
            otpAuthenticationProvider
        ));
    }

    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter(ConfigApi configApi,
                                                                   CaptchaApi captchaApi,
                                                                   CustomAuthenticationFailureHandler failureHandler) {
        return new CaptchaAuthenticationFilter(configApi, captchaApi, failureHandler);
    }

    @Bean
    public TokenSettings tokenSettings(JwtConfig config) {
        return TokenSettings.builder()
            .authorizationCodeTimeToLive(config.getAuthorizationCodeTimeToLive())
            .accessTokenTimeToLive(config.getAccessTokenTimeToLive())
            .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
            .deviceCodeTimeToLive(config.getDeviceCodeTimeToLive())
            .reuseRefreshTokens(true)
            .refreshTokenTimeToLive(config.getRefreshTokenTimeToLive())
            .idTokenSignatureAlgorithm(SignatureAlgorithm.RS256)
            .build();
    }

    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource, OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer) {
        JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
        jwtGenerator.setJwtCustomizer(tokenCustomizer);

        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();

        return new DelegatingOAuth2TokenGenerator(jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

}
