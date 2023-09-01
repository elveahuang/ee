package cn.elvea.platform.commons.core.oapis.baidu.cache;

import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface Cache {

    String get(String key);

    void set(String key, String value, Duration duration);

}
