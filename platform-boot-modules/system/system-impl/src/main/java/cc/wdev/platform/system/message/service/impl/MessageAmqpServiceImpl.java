package cc.wdev.platform.system.message.service.impl;

import cc.wdev.platform.commons.message.rabbit.AbstractAmqpService;
import cc.wdev.platform.system.commons.constants.SystemAmqpConstants;
import cc.wdev.platform.system.message.domain.dto.SendMessageAmqpDto;
import cc.wdev.platform.system.message.manager.MessageManager;
import cc.wdev.platform.system.message.service.MessageAmqpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see AbstractAmqpService
 * @see MessageAmqpService
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.MESSAGE_QUEUE)
public class MessageAmqpServiceImpl extends AbstractAmqpService<SendMessageAmqpDto> implements MessageAmqpService {

    private MessageManager messageManager;

    @Override
    public void execute(SendMessageAmqpDto dto) throws Exception {
        this.messageManager.sendMessage(dto.getId());
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.MESSAGE_QUEUE;
    }

    @Autowired
    public void setMessageApi(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

}
