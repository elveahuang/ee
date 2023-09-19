package cn.elvea.platform.system.core.api;

import cn.elvea.platform.system.core.model.dto.ConfigDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface ConfigApi {

    ConfigDto get(String key);

}
