package cc.elvea.platform.system.config.api.impl;

import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.config.api.ConfigApi;
import cc.elvea.platform.system.config.model.converter.ConfigConverter;
import cc.elvea.platform.system.config.model.dto.ConfigDto;
import cc.elvea.platform.system.config.service.ConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class ConfigApiImpl implements ConfigApi {

    private final ConfigService configService;

    @Override
    public ConfigDto get(String key) {
        return SpringUtils.getBean(ConfigConverter.class).entityToDto(this.configService.getConfigByKey(key));
    }

    @Override
    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        ConfigDto config = this.get(key);
        if (config != null) {
            return "true".equalsIgnoreCase(config.getConfigValue()) || "1".equalsIgnoreCase(config.getConfigValue());
        }
        return defaultValue;
    }

}
