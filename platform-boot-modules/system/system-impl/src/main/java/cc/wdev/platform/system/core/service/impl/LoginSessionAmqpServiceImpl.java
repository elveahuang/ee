package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.enums.ActionTypeEnum;
import cc.wdev.platform.commons.message.rabbit.AbstractAmqpService;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.commons.constants.SystemAmqpConstants;
import cc.wdev.platform.system.core.domain.converter.UserSessionConverter;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;
import cc.wdev.platform.system.core.domain.entity.LoginSessionEntity;
import cc.wdev.platform.system.core.service.LoginSessionAmqpService;
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
public class LoginSessionAmqpServiceImpl extends AbstractAmqpService<UserSessionDto> implements LoginSessionAmqpService {

    private final LoginSessionService userSessionService;

    public LoginSessionAmqpServiceImpl(LoginSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @Override
    public void execute(UserSessionDto dto) {
        LocalDateTime localDateTime = this.getCurLocalDateTime();
        LoginSessionEntity entity = this.userSessionService.findBySessionId(dto.getSessionId());
        if (ActionTypeEnum.DELETE.equals(dto.getActionType())) {
            if (entity != null) {
                entity.setEndDatetime(localDateTime);
                entity.setUpdatedBy(dto.getUserId());
                entity.setUpdatedAt(localDateTime);
                entity.setDeletedBy(dto.getUserId());
                entity.setDeletedAt(localDateTime);
                this.userSessionService.save(entity);
            }
        } else {
            if (entity != null) {
                entity.setLastAccessDatetime(localDateTime);
            } else {
                entity = SpringUtils.getBean(UserSessionConverter.class).dto2Entity(dto);
                entity.setStartDatetime(localDateTime);
                entity.setCreatedBy(dto.getUserId());
                entity.setCreatedAt(localDateTime);
            }
            entity.setUa(dto.getUa());
            entity.setHost(dto.getHost());
            entity.setLastAccessDatetime(localDateTime);
            entity.setUpdatedBy(dto.getUserId());
            entity.setUpdatedAt(localDateTime);
            this.userSessionService.save(entity);
        }
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.USER_SESSION;
    }

}
