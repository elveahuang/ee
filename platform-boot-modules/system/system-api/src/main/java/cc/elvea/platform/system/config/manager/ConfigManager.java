package cc.elvea.platform.system.config.manager;

import cc.elvea.platform.system.config.model.dto.ConfigDto;

/**
 * @author elvea
 */
public interface ConfigManager {

    ConfigDto get(String key);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

}
