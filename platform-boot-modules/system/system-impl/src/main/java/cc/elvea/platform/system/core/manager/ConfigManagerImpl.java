package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.core.domain.converter.ConfigConverter;
import cc.elvea.platform.system.core.domain.dto.ConfigDto;
import cc.elvea.platform.system.core.service.ConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class ConfigManagerImpl implements ConfigManager {

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
