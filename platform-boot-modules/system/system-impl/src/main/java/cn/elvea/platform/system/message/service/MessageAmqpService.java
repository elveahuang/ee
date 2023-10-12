package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.message.amqp.AmqpService;
import cn.elvea.platform.system.message.model.dto.SendMessageAmqpDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageAmqpService extends AmqpService<SendMessageAmqpDto> {
}
