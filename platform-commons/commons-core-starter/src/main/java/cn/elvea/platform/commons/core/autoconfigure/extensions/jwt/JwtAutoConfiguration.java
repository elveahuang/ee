package cn.elvea.platform.commons.core.autoconfigure.extensions.jwt;

import cn.elvea.platform.commons.core.autoconfigure.extensions.jwt.properties.JwtProperties;
import cn.elvea.platform.commons.core.extensions.jwt.JwtConfig;
import cn.elvea.platform.commons.core.extensions.jwt.JwtHelper;
import cn.elvea.platform.commons.core.utils.EncryptUtils;
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

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = JwtProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties({JwtProperties.class})
public class JwtAutoConfiguration {

    private final JwtProperties properties;

    JwtAutoConfiguration(JwtProperties properties) {
        this.properties = properties;
    }

    /**
     * @return {@link JWKSource}
     */
    @Bean
    @ConditionalOnMissingBean
    public JWK jwk() {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) EncryptUtils.toPublicKey(this.properties.getPublicKeyValue());
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) EncryptUtils.toPrivateKey(this.properties.getPrivateKeyValue());
        return new RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).build();
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
    public JwtHelper jwtHelper(JwtProperties properties, JwtEncoder encoder, JwtDecoder decoder) {
        JwtConfig config = new JwtConfig();
        config.setAlgorithm(properties.getAlgorithm());
        config.setPublicKeyValue(properties.getPublicKeyValue());
        config.setPrivateKeyValue(properties.getPrivateKeyValue());
        return new JwtHelper(config, encoder, decoder);
    }

}
