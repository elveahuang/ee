package cc.elvea.platform.commons.message.rabbit;

import cc.elvea.platform.commons.core.Context;
import cc.elvea.platform.commons.service.AbstractService;
import cc.elvea.platform.commons.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author elvea
 */
@Slf4j
@Transactional
public abstract class AbstractAmqpService<T> extends AbstractService implements AmqpService<T> {

    protected Context context;

    protected RabbitTemplate rabbitTemplate;

    /**
     * @see AmqpService#send(Object)
     */
    @Override
    public void send(T body) throws Exception {
        log.info("Send Amqp Message - {}", JacksonUtils.toJson(body));
        this.send(getRoutingKey(), body);
    }

    /**
     * @see AmqpService#send(String, Object)
     */
    @Override
    public void send(String routingKey, T body) throws Exception {
        if (this.isEnabled()) {
            log.info("RabbitMQ is enabled. send...");
            this.rabbitTemplate.convertAndSend(routingKey, body);
        } else {
            log.info("RabbitMQ is disabled. execute...");
            this.execute(body);
        }
    }

    /**
     * @see AmqpService#send(String, Object)
     */
    @Override
    public void send(String exchange, String routingKey, T body) throws Exception {
        if (this.isEnabled()) {
            log.info("RabbitMQ is enabled. send...");
            this.rabbitTemplate.convertAndSend(exchange, routingKey, body);
        } else {
            log.info("RabbitMQ is disabled. execute...");
            this.execute(body);
        }
    }

    @RabbitHandler
    public void handler(T t) {
        try {
            execute(t);
        } catch (Exception e) {
            log.error("RabbitMQ error.", e);
        }
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    public void setContext(Context context) {
        this.context = context;
    }

    protected boolean isEnabled() {
        return context.isAmqpEnabled();
    }

    public abstract void execute(T t) throws Exception;

    public abstract String getRoutingKey();

}
