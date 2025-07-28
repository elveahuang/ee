package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.message.rabbit.AmqpService;
import cc.elvea.platform.system.message.domain.dto.SendMessageAmqpDto;

/**
 * @author elvea
 */
public interface MessageAmqpService extends AmqpService<SendMessageAmqpDto> {
}
