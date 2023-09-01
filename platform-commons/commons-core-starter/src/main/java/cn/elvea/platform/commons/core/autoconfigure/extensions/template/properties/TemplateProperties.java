package cn.elvea.platform.commons.core.autoconfigure.extensions.template.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(TemplateProperties.PREFIX)
public class TemplateProperties {

    public static final String PREFIX = "platform.template";

    private Boolean enabled = Boolean.FALSE;

}
