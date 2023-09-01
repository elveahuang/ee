package cn.elvea.platform.commons.core.autoconfigure.data.mongodb.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomMongoProperties.PREFIX)
public class CustomMongoProperties {

    public static final String PREFIX = "platform.data.mongodb";

    private boolean enabled = false;

}
