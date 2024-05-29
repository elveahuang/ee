package cc.elvea.platform.commons.autoconfigure.cache.properties;

import cc.elvea.platform.commons.cache.enums.CacheType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = CustomCacheProperties.PREFIX)
public class CustomCacheProperties {

    public static final String PREFIX = "platform.cache";

    private boolean enabled = false;

    private CacheType type = CacheType.Redis;

    private boolean cacheNullValue = true;

    private int batchSize = 1000;

    private Duration timeToLive = Duration.ofHours(1);

}
