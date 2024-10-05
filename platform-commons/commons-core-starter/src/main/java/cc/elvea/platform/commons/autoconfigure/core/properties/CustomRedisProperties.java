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
@ConfigurationProperties(prefix = CustomRedisProperties.PREFIX)
public class CustomRedisProperties {

    public static final String PREFIX = "platform.cache.redis";

    private boolean enabled = false;

    private boolean springCacheEnabled = false;

}
