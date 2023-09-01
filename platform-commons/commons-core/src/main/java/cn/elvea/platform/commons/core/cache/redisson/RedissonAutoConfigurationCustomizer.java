package cn.elvea.platform.commons.core.cache.redisson;

import org.redisson.config.Config;

/**
 * @author elvea
 * @since 0.0.1
 */
@FunctionalInterface
public interface RedissonAutoConfigurationCustomizer {

    void customize(final Config configuration);

}
