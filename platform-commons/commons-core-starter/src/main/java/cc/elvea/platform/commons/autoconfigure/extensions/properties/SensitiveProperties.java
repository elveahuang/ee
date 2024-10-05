package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SensitiveProperties.PREFIX)
public class SensitiveProperties {

    public static final String PREFIX = "platform.sensitive";

    private boolean enabled = true;

}
