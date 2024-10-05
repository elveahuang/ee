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
@ConfigurationProperties(prefix = CustomRedissonProperties.PREFIX)
public class CustomRedissonProperties {

    public static final String PREFIX = "platform.cache.redisson";

    private boolean enabled = false;

    private boolean springCacheEnabled = false;

    private int maxActive = 32;

    private int maxIdle = 8;

    private int minIdle = 0;

}
