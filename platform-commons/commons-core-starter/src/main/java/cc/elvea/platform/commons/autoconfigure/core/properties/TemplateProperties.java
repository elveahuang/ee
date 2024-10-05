package cc.elvea.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(TemplateProperties.PREFIX)
public class TemplateProperties {

    public static final String PREFIX = "platform.template";

    private boolean enabled = false;

}
