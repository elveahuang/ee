package cc.elvea.platform.commons.autoconfigure.data.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(JdbcCustomProperties.PREFIX)
public class JdbcCustomProperties {

    public static final String PREFIX = "platform.data.jdbc";

    private boolean enabled = false;

}
