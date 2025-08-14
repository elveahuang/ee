package top.baihu.platform.system.message.service;

import top.baihu.platform.commons.message.rabbit.AmqpService;
import top.baihu.platform.system.message.domain.dto.SendMessageAmqpDto;

/**
 * @author elvea
 */
public interface MessageAmqpService extends AmqpService<SendMessageAmqpDto> {
}
