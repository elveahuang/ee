package cn.elvea.platform.commons.core.autoconfigure.data.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomDataProperties.PREFIX)
public class CustomDataProperties {

    public static final String PREFIX = "platform.data";

    private Boolean enabled = Boolean.FALSE;

}
