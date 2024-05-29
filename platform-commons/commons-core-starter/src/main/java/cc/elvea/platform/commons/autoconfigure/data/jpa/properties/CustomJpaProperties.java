package cc.elvea.platform.commons.autoconfigure.data.jpa.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomJpaProperties.PREFIX)
public class CustomJpaProperties {

    public static final String PREFIX = "platform.data.jpa";

    private boolean enabled = false;

    private boolean showSql = true;

}
