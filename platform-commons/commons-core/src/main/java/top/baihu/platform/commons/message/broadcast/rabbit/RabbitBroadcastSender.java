package top.baihu.platform.commons.message.broadcast.rabbit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import top.baihu.platform.commons.message.broadcast.BroadcastEvent;
import top.baihu.platform.commons.message.broadcast.BroadcastMessage;
import top.baihu.platform.commons.message.broadcast.BroadcastSender;
import top.baihu.platform.commons.utils.JacksonUtils;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class RabbitBroadcastSender implements BroadcastSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * @see BroadcastSender#sendMessage(BroadcastMessage)
     */
    @Override
    public <E extends BroadcastMessage<?, ? extends BroadcastEvent<?>>> void sendMessage(E message) throws Exception {
        log.info("Send rabbit broadcast message : {}", JacksonUtils.toJson(message));
        this.rabbitTemplate.convertAndSend("broadcastExchange", "", message);
    }

}
