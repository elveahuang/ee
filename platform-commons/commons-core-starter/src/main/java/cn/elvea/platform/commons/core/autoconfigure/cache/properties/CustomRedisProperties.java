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
@ConfigurationProperties(prefix = CustomRedisProperties.PREFIX)
public class CustomRedisProperties {

    public static final String PREFIX = "platform.cache.redis";

    private Boolean enabled = Boolean.FALSE;

    private Boolean springCacheEnabled = Boolean.FALSE;

}
