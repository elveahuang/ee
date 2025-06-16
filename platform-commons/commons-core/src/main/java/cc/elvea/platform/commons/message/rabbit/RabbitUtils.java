package cc.elvea.platform.commons.message.rabbit;

import cc.elvea.platform.commons.core.Context;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class RabbitUtils {

    protected Context context;

    protected RabbitTemplate rabbitTemplate;

    public <T> void send(String routingKey, T body) throws Exception {
        this.rabbitTemplate.convertAndSend(routingKey, body);
    }

    public <T> void send(String exchange, String routingKey, T body) throws Exception {
        this.rabbitTemplate.convertAndSend(exchange, routingKey, body);
    }

}
