package cc.wdev.platform.system.message.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.message.domain.entity.MessageTemplateTypeEntity;

/**
 * @author elvea
 */
public interface MessageTemplateTypeService extends CachingEntityService<MessageTemplateTypeEntity, Long> {

    MessageTemplateTypeEntity findByCode(String key);

}
