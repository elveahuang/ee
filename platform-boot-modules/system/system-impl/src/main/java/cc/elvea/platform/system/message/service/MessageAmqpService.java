package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.message.amqp.AmqpService;
import cc.elvea.platform.system.message.model.dto.SendMessageAmqpDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MessageAmqpService extends AmqpService<SendMessageAmqpDto> {
}
