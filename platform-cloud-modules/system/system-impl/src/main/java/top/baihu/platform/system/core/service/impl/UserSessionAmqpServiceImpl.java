package top.baihu.platform.system.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.message.rabbit.AbstractAmqpService;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.commons.constants.SystemAmqpConstants;
import top.baihu.platform.system.core.domain.converter.UserSessionConverter;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;
import top.baihu.platform.system.core.domain.entity.UserSessionEntity;
import top.baihu.platform.system.core.service.UserSessionAmqpService;
import top.baihu.platform.system.core.service.UserSessionService;

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
