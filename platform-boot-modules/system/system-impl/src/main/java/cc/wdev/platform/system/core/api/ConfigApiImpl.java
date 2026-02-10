package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.core.domain.converter.ConfigConverter;
import cc.wdev.platform.system.core.domain.dto.ConfigDto;
import cc.wdev.platform.system.core.service.ConfigService;
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
