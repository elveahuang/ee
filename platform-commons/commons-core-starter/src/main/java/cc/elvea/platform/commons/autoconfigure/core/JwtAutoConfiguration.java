package cc.elvea.platform.commons.autoconfigure.core;

import cc.elvea.platform.commons.autoconfigure.core.properties.JwtProperties;
import cc.elvea.platform.commons.utils.EncryptUtils;
import cc.elvea.platform.commons.utils.jwt.JwtConfig;
import cc.elvea.platform.commons.utils.jwt.JwtHelper;
import cc.elvea.platform.commons.utils.jwt.JwtStrategy;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = JwtProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({JwtProperties.class})
public class JwtAutoConfiguration {

    private final JwtProperties properties;

    public JwtAutoConfiguration(JwtProperties properties) {
        log.info("JwtAutoConfiguration is enabled.");
        this.properties = properties;
    }

    /**
     * @return {@link JwtConfig}
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtConfig config() {
        return JwtConfig.builder()
                .enabled(this.properties.isEnabled())
                .accessTokenTimeToLive(this.properties.getAccessTokenTimeToLive())
                .refreshTokenTimeToLive(this.properties.getRefreshTokenTimeToLive())
                .build();
    }

    /**
     * @return {@link JWK}
     */
    @Bean
    @ConditionalOnMissingBean
    public JWK jwk() {
        if (JwtStrategy.MANUEL.equals(this.properties.getStrategy())) {
            RSAPublicKey publicKey = (RSAPublicKey) EncryptUtils.toPublicKey(this.properties.getPublicKeyValue());
            RSAPrivateKey privateKey = (RSAPrivateKey) EncryptUtils.toPrivateKey(this.properties.getPrivateKeyValue());
            return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
        } else {
            KeyPair keyPair = EncryptUtils.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            return new RSAKey.Builder(publicKey).privateKey(privateKey).keyID(UUID.randomUUID().toString()).build();
        }
    }

    /**
     * @return {@link JWKSource}
     */
    @Bean
    @ConditionalOnMissingBean
    public JWKSource<SecurityContext> jwkSource(JWK jwk) {
        return new ImmutableJWKSet<>(new JWKSet(jwk));
    }

    /**
     * @return {@link JwtEncoder}
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    /**
     * @return {@link JwtEncoder}
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtDecoder jwtDecoder(JWK jwk) throws Exception {
        return NimbusJwtDecoder.withPublicKey(jwk.toRSAKey().toRSAPublicKey())
                .signatureAlgorithm(SignatureAlgorithm.from(this.properties.getAlgorithm()))
                .build();
    }

    /**
     * @return {@link JwtHelper}
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtHelper jwtHelper(JwtConfig config, JwtEncoder encoder, JwtDecoder decoder) {
        return new JwtHelper(config, encoder, decoder);
    }

}
