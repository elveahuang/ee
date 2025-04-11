package cc.elvea.platform.system.config.api;

import cc.elvea.platform.system.config.model.dto.ConfigDto;

/**
 * @author elvea
 */
public interface ConfigApi {

    ConfigDto get(String key);

    boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultValue);

}
