package cc.wdev.platform.system.message.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.message.domain.entity.MessageTemplateEntity;

/**
 * @author elvea
 */
public interface MessageTemplateService extends CachingEntityService<MessageTemplateEntity, Long> {

    MessageTemplateEntity findByType(Long typeId, Long templateTypeId);

}
