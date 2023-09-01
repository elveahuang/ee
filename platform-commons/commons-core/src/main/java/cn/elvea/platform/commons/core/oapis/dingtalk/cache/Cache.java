package cn.elvea.platform.commons.core.oapis.dingtalk.cache;

import java.util.concurrent.TimeUnit;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface Cache {

    String get(String key);

    void set(String key, String value, long expire, TimeUnit timeUnit);

}
