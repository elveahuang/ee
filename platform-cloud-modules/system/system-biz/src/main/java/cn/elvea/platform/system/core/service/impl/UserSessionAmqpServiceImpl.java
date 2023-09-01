package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.message.amqp.AbstractAmqpService;
import cn.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cn.elvea.platform.system.core.model.converter.UserSessionConverter;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;
import cn.elvea.platform.system.core.model.entity.UserSessionEntity;
import cn.elvea.platform.system.core.service.UserSessionAmqpService;
import cn.elvea.platform.system.core.service.UserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.USER_SESSION)
public class UserSessionAmqpServiceImpl extends AbstractAmqpService<UserSessionDto> implements UserSessionAmqpService {

    private final UserSessionService userSessionService;

    public UserSessionAmqpServiceImpl(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @Override
    public void execute(UserSessionDto dto) {
        UserSessionEntity entity = UserSessionConverter.INSTANCE.dto2Entity(dto);
        this.userSessionService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.USER_SESSION;
    }

}
