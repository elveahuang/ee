package cc.elvea.platform.system.commons.manager;

import cc.elvea.platform.system.commons.model.dto.ConfigDto;

/**
 * @author elvea
 */
public interface ConfigManager {

    ConfigDto get(String key);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

}
