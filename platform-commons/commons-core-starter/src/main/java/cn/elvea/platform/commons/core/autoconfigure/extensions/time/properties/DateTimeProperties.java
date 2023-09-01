package cn.elvea.platform.commons.core.autoconfigure.extensions.time.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = DateTimeProperties.PREFIX)
public class DateTimeProperties implements Serializable {

    public static final String PREFIX = "platform.ra";

    private boolean enabled = false;

}
