package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.message.rabbit.AbstractAmqpService;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cc.elvea.platform.system.core.model.converter.UserSessionConverter;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;
import cc.elvea.platform.system.core.service.UserSessionAmqpService;
import cc.elvea.platform.system.core.service.UserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author elvea
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
        UserSessionEntity entity = SpringUtils.getBean(UserSessionConverter.class).dto2Entity(dto);
        this.userSessionService.save(entity);
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.USER_SESSION;
    }

}
