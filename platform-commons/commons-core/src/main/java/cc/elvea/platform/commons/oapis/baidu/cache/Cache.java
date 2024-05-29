package cc.elvea.platform.commons.oapis.baidu.cache;

import java.time.Duration;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface Cache {

    String get(String key);

    void set(String key, String value, Duration duration);

}
