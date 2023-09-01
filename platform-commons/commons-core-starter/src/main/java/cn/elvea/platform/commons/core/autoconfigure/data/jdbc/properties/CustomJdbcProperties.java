package cn.elvea.platform.commons.core.autoconfigure.data.jdbc.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomJdbcProperties.PREFIX)
public class CustomJdbcProperties {

    public static final String PREFIX = "platform.data.jdbc";

    private Boolean enabled = Boolean.FALSE;

}
