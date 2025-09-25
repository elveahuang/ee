package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.message.rabbit.RabbitService;
import cc.wdev.platform.system.core.domain.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionRabbitService extends RabbitService<UserSessionDto> {
}
