package cc.elvea.platform.commons.oapis.dingtalk.cache;

import java.util.concurrent.TimeUnit;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface Cache {

    String get(String key);

    void set(String key, String value, long expire, TimeUnit timeUnit);

}
