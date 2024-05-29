package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.message.amqp.AmqpService;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UserSessionAmqpService extends AmqpService<UserSessionDto> {
}
