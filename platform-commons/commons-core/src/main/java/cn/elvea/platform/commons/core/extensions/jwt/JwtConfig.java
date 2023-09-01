package cn.elvea.platform.commons.core.extensions.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.jose.jws.JwsAlgorithms;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class JwtConfig {

    private String algorithm = JwsAlgorithms.RS256;

    private String publicKeyValue;

    private String privateKeyValue;

}
