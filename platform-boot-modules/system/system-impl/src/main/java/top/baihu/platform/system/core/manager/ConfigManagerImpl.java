package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.core.domain.converter.ConfigConverter;
import top.baihu.platform.system.core.domain.dto.ConfigDto;
import top.baihu.platform.system.core.service.ConfigService;

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
