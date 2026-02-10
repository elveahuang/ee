package cc.wdev.platform.system.message.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.message.domain.entity.MessageTypeEntity;

/**
 * @author elvea
 */
public interface MessageTypeService extends CachingEntityService<MessageTypeEntity, Long> {

    MessageTypeEntity findByCode(String key);

}
