package cn.elvea.platform.commons.core.autoconfigure.cache.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = CustomRedissonProperties.PREFIX)
public class CustomRedissonProperties {

    public static final String PREFIX = "platform.cache.redisson";

    private Boolean enabled = Boolean.FALSE;

    private Boolean springCacheEnabled = Boolean.FALSE;

    private int maxActive = 32;

    private int maxIdle = 8;

    private int minIdle = 0;

}
