package top.baihu.platform.system.message.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.message.domain.entity.MessageTemplateEntity;

/**
 * @author elvea
 */
public interface MessageTemplateService extends CachingEntityService<MessageTemplateEntity, Long> {

    MessageTemplateEntity findByType(Long typeId, Long templateTypeId);

}
