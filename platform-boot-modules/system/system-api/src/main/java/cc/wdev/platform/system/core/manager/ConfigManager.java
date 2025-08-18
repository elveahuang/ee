package cc.wdev.platform.system.core.manager;

import cc.wdev.platform.system.core.domain.dto.ConfigDto;

/**
 * @author elvea
 */
public interface ConfigManager {

    ConfigDto get(String key);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

}
