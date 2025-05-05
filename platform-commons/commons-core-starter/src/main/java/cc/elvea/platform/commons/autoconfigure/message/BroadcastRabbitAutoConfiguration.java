package cc.elvea.platform.commons.autoconfigure.message;

import cc.elvea.platform.commons.autoconfigure.message.properties.BroadcastProperties;
import cc.elvea.platform.commons.message.broadcast.rabbit.RabbitBroadcastListener;
import cc.elvea.platform.commons.message.broadcast.rabbit.RabbitBroadcastSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = BroadcastProperties.PREFIX_RABBIT, name = "enabled", havingValue = "true")
public class BroadcastRabbitAutoConfiguration {

    public BroadcastRabbitAutoConfiguration() {
        log.info("BroadcastRabbitAutoConfiguration is enabled.");
    }

    @Bean(name = "broadcastExchange")
    public FanoutExchange broadcastExchange(BroadcastProperties properties) {
        return new FanoutExchange(properties.getRabbit().getExchange());
    }

    @Bean(name = "broadcastQueue")
    public Queue broadcastQueue(BroadcastProperties properties) {
        return new Queue(properties.getRabbit().getQueue());
    }

    @Bean
    Binding broadcastBinding(@Qualifier("broadcastQueue") Queue broadcastQueue,
                             @Qualifier("broadcastExchange") FanoutExchange broadcastExchange) {
        return BindingBuilder.bind(broadcastQueue).to(broadcastExchange);
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitBroadcastSender rabbitBroadcastSender(RabbitTemplate rabbitTemplate) {
        return new RabbitBroadcastSender(rabbitTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitBroadcastListener rabbitBroadcastListener(ApplicationEventPublisher publisher) {
        return new RabbitBroadcastListener(publisher);
    }

}
