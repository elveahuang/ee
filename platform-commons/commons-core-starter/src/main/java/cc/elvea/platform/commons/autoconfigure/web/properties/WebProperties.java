package cc.elvea.platform.commons.autoconfigure.web.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(WebProperties.PREFIX)
public class WebProperties implements Serializable {

    public static final String PREFIX = "platform.web";

    private boolean enabled = false;

}
