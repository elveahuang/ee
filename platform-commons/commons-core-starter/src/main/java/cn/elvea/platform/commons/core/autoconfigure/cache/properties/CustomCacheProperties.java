package cn.elvea.platform.commons.core.autoconfigure.cache.properties;

import cn.elvea.platform.commons.core.cache.enums.CacheType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = CustomCacheProperties.PREFIX)
public class CustomCacheProperties {

    public static final String PREFIX = "platform.cache";

    private Boolean enabled = Boolean.FALSE;

    private CacheType type = CacheType.Redis;

    private Boolean cacheNullValue = Boolean.TRUE;

    private int batchSize = 1000;

    private Duration timeToLive = Duration.ofHours(1);

}
