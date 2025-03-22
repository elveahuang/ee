package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.message.model.entity.MessageTypeEntity;

/**
 * @author elvea
 */
public interface MessageTypeService extends CachingEntityService<MessageTypeEntity, Long> {

    MessageTypeEntity findByCode(String key);

}
