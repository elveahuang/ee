package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.CachingEntityService;
import cn.elvea.platform.system.message.model.entity.MessageTemplateEntity;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageTemplateService extends CachingEntityService<MessageTemplateEntity, Long> {

    MessageTemplateEntity findByType(Long typeId, Long templateTypeId);

}
