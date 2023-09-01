package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.system.message.model.entity.MessageUserEntity;
import cn.elvea.platform.system.message.repository.MessageUserRepository;
import cn.elvea.platform.system.message.service.MessageUserService;
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
public class MessageUserServiceImpl
        extends BaseEntityService<MessageUserEntity, Long, MessageUserRepository> implements MessageUserService {
}
