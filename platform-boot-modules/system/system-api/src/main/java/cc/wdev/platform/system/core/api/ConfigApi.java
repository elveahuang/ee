package cc.wdev.platform.system.core.feign;

import cc.wdev.platform.system.core.domain.dto.ConfigDto;

/**
 * @author elvea
 */
public interface ConfigApi {

    ConfigDto get(String key);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

}
