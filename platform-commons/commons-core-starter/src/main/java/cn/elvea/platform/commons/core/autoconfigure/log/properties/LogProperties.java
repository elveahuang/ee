package cn.elvea.platform.commons.core.autoconfigure.log.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(LogProperties.PREFIX)
public class LogProperties {

    public static final String PREFIX = "platform.log";

    private Boolean enabled = Boolean.FALSE;

}
