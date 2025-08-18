package cc.wdev.platform.commons.message.broadcast.rabbit;

import cc.wdev.platform.commons.message.broadcast.BroadcastEvent;
import cc.wdev.platform.commons.message.broadcast.BroadcastMessage;
import cc.wdev.platform.commons.message.broadcast.BroadcastSender;
import cc.wdev.platform.commons.utils.JacksonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

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
