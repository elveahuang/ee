package cn.elvea.platform.system.core.api.impl;

import cn.elvea.platform.system.core.api.ConfigApi;
import cn.elvea.platform.system.core.model.converter.ConfigConverter;
import cn.elvea.platform.system.core.model.dto.ConfigDto;
import cn.elvea.platform.system.core.service.ConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Service
@AllArgsConstructor
public class ConfigApiImpl implements ConfigApi {

    private final ConfigService configService;

    @Override
    public ConfigDto get(String key) {
        return ConfigConverter.INSTANCE.entityToDto(this.configService.getConfigByKey(key));
    }

}
