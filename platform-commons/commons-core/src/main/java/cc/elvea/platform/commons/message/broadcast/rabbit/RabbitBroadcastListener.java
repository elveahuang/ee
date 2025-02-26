package cc.elvea.platform.commons.message.broadcast.rabbit;

import cc.elvea.platform.commons.message.broadcast.BroadcastEvent;
import cc.elvea.platform.commons.message.broadcast.BroadcastMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@RabbitListener(queues = "broadcastQueue")
public class RabbitBroadcastListener {

    private ApplicationEventPublisher publisher;

    @RabbitHandler
    public <E extends BroadcastMessage<?, ? extends BroadcastEvent<?>>> void process(E message) {
        publisher.publishEvent(message.toEvent());
    }

}
