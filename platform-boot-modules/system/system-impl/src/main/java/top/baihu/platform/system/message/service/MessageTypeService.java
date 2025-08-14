package top.baihu.platform.system.message.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.message.domain.entity.MessageTypeEntity;

/**
 * @author elvea
 */
public interface MessageTypeService extends CachingEntityService<MessageTypeEntity, Long> {

    MessageTypeEntity findByCode(String key);

}
