package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.system.message.model.entity.MessageTemplateEntity;
import cn.elvea.platform.system.message.repository.MessageTemplateRepository;
import cn.elvea.platform.system.message.service.MessageTemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class MessageTemplateServiceImpl
        extends BaseEntityService<MessageTemplateEntity, Long, MessageTemplateRepository>
        implements MessageTemplateService {
}
