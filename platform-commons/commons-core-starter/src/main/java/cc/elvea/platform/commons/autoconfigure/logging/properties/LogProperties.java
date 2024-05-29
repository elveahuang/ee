package cc.elvea.platform.commons.autoconfigure.logging.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(LogProperties.PREFIX)
public class LogProperties {

    public static final String PREFIX = "platform.log";

    private boolean enabled = true;

}
