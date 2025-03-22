package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.message.rabbit.AmqpService;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionAmqpService extends AmqpService<UserSessionDto> {
}
