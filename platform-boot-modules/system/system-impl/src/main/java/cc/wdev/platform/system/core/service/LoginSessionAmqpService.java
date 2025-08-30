package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.message.rabbit.AmqpService;
import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;

/**
 * @author elvea
 */
public interface LoginSessionAmqpService extends AmqpService<LoginSessionDto> {
}
