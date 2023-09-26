package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.message.model.entity.MessageTemplateEntity;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageTemplateService extends EntityService<MessageTemplateEntity, Long> {

    MessageTemplateEntity findByType(Long typeId, Long templateTypeId);

}
