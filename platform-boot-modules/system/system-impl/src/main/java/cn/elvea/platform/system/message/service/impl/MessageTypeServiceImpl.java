package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.system.message.model.entity.MessageTypeEntity;
import cn.elvea.platform.system.message.repository.MessageTypeRepository;
import cn.elvea.platform.system.message.service.MessageTypeService;
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
public class MessageTypeServiceImpl
        extends BaseEntityService<MessageTypeEntity, Long, MessageTypeRepository> implements MessageTypeService {
}
