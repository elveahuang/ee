package top.baihu.platform.security.configuration;

import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import top.baihu.platform.commons.utils.jwt.JwtConfig;
import top.baihu.platform.security.CustomAccountDetailsService;
import top.baihu.platform.security.CustomAuthenticationProvider;
import top.baihu.platform.security.CustomUserDetailsService;
import top.baihu.platform.security.web.CustomAuthenticationFailureHandler;
import top.baihu.platform.security.web.authentication.CaptchaAuthenticationFilter;
import top.baihu.platform.system.core.manager.CaptchaManager;
import top.baihu.platform.system.core.manager.ConfigManager;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class CommonSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CaptchaAuthenticationFilter captchaAuthenticationFilter(ConfigManager configManager,
                                                                   CaptchaManager captchaManager,
                                                                   CustomAuthenticationFailureHandler failureHandler) {
        return new CaptchaAuthenticationFilter(configManager, captchaManager, failureHandler);
    }

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
    public AuthenticationProvider authenticationProvider(
        @Qualifier("userDetailsService") CustomUserDetailsService userDetailsService,
        @Qualifier("accountDetailsService") CustomAccountDetailsService accountDetailsService,
        PasswordEncoder passwordEncoder) {
        return new CustomAuthenticationProvider(userDetailsService, accountDetailsService, passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
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
