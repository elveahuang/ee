package cc.wdev.platform.system.message.service;

import cc.wdev.platform.commons.message.rabbit.AmqpService;
import cc.wdev.platform.system.message.domain.dto.SendMessageAmqpDto;

/**
 * @author elvea
 */
public interface MessageAmqpService extends AmqpService<SendMessageAmqpDto> {
}
