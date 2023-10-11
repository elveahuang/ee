package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.message.model.entity.MessageTemplateTypeEntity;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageTemplateTypeService extends CachingEntityService<MessageTemplateTypeEntity, Long> {

    MessageTemplateTypeEntity findByCode(String key);

}
