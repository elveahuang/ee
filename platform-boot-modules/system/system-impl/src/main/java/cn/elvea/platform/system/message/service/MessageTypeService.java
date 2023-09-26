package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.message.model.entity.MessageTypeEntity;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageTypeService extends CachingEntityService<MessageTypeEntity, Long> {

    MessageTypeEntity findByCode(String key);

}
