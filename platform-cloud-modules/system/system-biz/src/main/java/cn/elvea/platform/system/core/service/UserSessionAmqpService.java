package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.message.amqp.AmqpService;
import cn.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface UserSessionAmqpService extends AmqpService<UserSessionDto> {
}
