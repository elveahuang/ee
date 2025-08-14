package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.message.rabbit.AmqpService;
import top.baihu.platform.system.core.domain.dto.UserSessionDto;

/**
 * @author elvea
 */
public interface UserSessionAmqpService extends AmqpService<UserSessionDto> {
}
