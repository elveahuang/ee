package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.message.rabbit.AbstractRabbitService;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.commons.constants.SystemAmqpConstants;
import cc.wdev.platform.system.core.domain.converter.UserSessionConverter;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;
import cc.wdev.platform.system.core.domain.entity.UserSessionEntity;
import cc.wdev.platform.system.core.service.UserSessionRabbitService;
import cc.wdev.platform.system.core.service.UserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.USER_SESSION)
public class UserSessionRabbitServiceImpl extends AbstractRabbitService<UserSessionDto> implements UserSessionRabbitService {

    private final UserSessionService userSessionService;

    public UserSessionRabbitServiceImpl(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @Override
    public void execute(UserSessionDto dto) {
        UserSessionEntity entity = SpringUtils.getBean(UserSessionConverter.class).dto2Entity(dto);
        this.userSessionService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.USER_SESSION;
    }

}
