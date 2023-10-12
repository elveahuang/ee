package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.message.amqp.AbstractAmqpService;
import cn.elvea.platform.system.commons.constants.SystemAmqpConstants;
import cn.elvea.platform.system.message.api.MessageApi;
import cn.elvea.platform.system.message.model.dto.SendMessageAmqpDto;
import cn.elvea.platform.system.message.service.MessageAmqpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see AbstractAmqpService
 * @see MessageAmqpService
 * @since 0.0.1
 */
@Slf4j
@Service
@RabbitListener(queues = SystemAmqpConstants.MESSAGE_QUEUE)
public class MessageAmqpServiceImpl extends AbstractAmqpService<SendMessageAmqpDto> implements MessageAmqpService {

    private MessageApi messageApi;

    @Override
    public void execute(SendMessageAmqpDto dto) throws Exception {
        this.messageApi.sendMessage(dto.getId());
    }

    @Override
    public String getRoutingKey() {
        return SystemAmqpConstants.MESSAGE_QUEUE;
    }

    @Autowired
    public void setMessageApi(MessageApi messageApi) {
        this.messageApi = messageApi;
    }

}
