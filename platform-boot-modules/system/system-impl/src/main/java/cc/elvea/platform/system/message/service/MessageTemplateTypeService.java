package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.message.model.entity.MessageTemplateTypeEntity;

/**
 * @author elvea
 */
public interface MessageTemplateTypeService extends CachingEntityService<MessageTemplateTypeEntity, Long> {

    MessageTemplateTypeEntity findByCode(String key);

}
