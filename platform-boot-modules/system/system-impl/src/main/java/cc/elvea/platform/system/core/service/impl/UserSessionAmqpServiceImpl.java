package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.enums.ActionTypeEnum;
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

import java.time.LocalDateTime;

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
        LocalDateTime localDateTime = this.getCurLocalDateTime();
        UserSessionEntity entity = this.userSessionService.findBySessionId(dto.getSessionId());
        if (ActionTypeEnum.DELETE.equals(dto.getActionType())) {
            if (entity != null) {
                entity.setEndDatetime(localDateTime);
                entity.setLastModifiedBy(dto.getUserId());
                entity.setLastModifiedAt(localDateTime);
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
            entity.setLastModifiedBy(dto.getUserId());
            entity.setLastModifiedAt(localDateTime);
            this.userSessionService.save(entity);
        }
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.USER_SESSION;
    }

}
