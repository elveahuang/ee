package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.enums.ActionTypeEnum;
import cc.wdev.platform.commons.message.rabbit.AbstractRabbitService;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.commons.constants.SystemAmqpConstants;
import cc.wdev.platform.system.core.domain.converter.UserSessionConverter;
import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;
import cc.wdev.platform.system.core.domain.entity.LoginSessionEntity;
import cc.wdev.platform.system.core.service.LoginSessionRabbitService;
import cc.wdev.platform.system.core.service.LoginSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.USER_SESSION)
public class LoginSessionRabbitServiceImpl extends AbstractRabbitService<LoginSessionDto> implements LoginSessionRabbitService {

    private final LoginSessionService loginSessionService;

    public LoginSessionRabbitServiceImpl(LoginSessionService loginSessionService) {
        this.loginSessionService = loginSessionService;
    }

    @Override
    public void execute(LoginSessionDto dto) {
        LocalDateTime localDateTime = this.getCurLocalDateTime();
        LoginSessionEntity entity = this.loginSessionService.findBySessionId(dto.getSessionId());
        if (ActionTypeEnum.DELETE.equals(dto.getActionType())) {
            if (entity != null) {
                entity.setEndDatetime(localDateTime);
                entity.setUpdatedBy(dto.getEntityId());
                entity.setUpdatedAt(localDateTime);
                entity.setDeletedBy(dto.getEntityId());
                entity.setDeletedAt(localDateTime);
                this.loginSessionService.save(entity);
            }
        } else {
            if (entity != null) {
                entity.setLastAccessDatetime(localDateTime);
            } else {
                entity = SpringUtils.getBean(UserSessionConverter.class).dto2Entity(dto);
                entity.setStartDatetime(localDateTime);
                entity.setCreatedBy(dto.getEntityId());
                entity.setCreatedAt(localDateTime);
            }
            entity.setUa(dto.getUa());
            entity.setHost(dto.getHost());
            entity.setLastAccessDatetime(localDateTime);
            entity.setUpdatedBy(dto.getEntityId());
            entity.setUpdatedAt(localDateTime);
            this.loginSessionService.save(entity);
        }
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.USER_SESSION;
    }

}
