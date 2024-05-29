package cc.elvea.platform.commons.cache.redisson;

import org.redisson.config.Config;

/**
 * @author elvea
 * @since 24.1.0
 */
@FunctionalInterface
public interface RedissonAutoConfigurationCustomizer {

    void customize(final Config configuration);

}
