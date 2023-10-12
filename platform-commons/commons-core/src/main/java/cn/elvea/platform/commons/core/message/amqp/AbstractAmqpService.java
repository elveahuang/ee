package cn.elvea.platform.commons.core.message.amqp;

import cn.elvea.platform.commons.core.context.Context;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class AbstractAmqpService<T> implements AmqpService<T> {

    protected Context context;

    protected RabbitTemplate rabbitTemplate;

    /**
     * @see AmqpService#send(Object)
     */
    @Override
    public void send(T body) throws Exception {
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
