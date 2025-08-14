package top.baihu.platform.system.message.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.message.domain.entity.MessageTemplateTypeEntity;

/**
 * @author elvea
 */
public interface MessageTemplateTypeService extends CachingEntityService<MessageTemplateTypeEntity, Long> {

    MessageTemplateTypeEntity findByCode(String key);

}
