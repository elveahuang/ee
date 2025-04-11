package cc.elvea.platform.system.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cc.elvea.platform.system.commons.constants.SystemAmqpConstants.*;

/**
 * @author elvea
 */
@Slf4j
@Configuration
public class SystemAmqpConfiguration {

    /**
     * 替换默认转换器
     * 解决消息监听可能出现反序列化失败的问题
     */
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue userSessionQueue() {
        return new Queue(USER_SESSION);
    }

    @Bean
    public Queue captchaLogQueue() {
        return new Queue(CAPTCHA_LOG_QUEUE);
    }

    @Bean
    public Queue urlLogQueue() {
        return new Queue(URL_LOG_QUEUE);
    }

    @Bean
    public Queue operationLogQueue() {
        return new Queue(OPERATION_LOG_QUEUE);
    }

    @Bean
    public Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE);
    }

    // --------------------------------------------------------------------------------------------------------------------------------
    // 用于控制是否允许多处登录
    // --------------------------------------------------------------------------------------------------------------------------------

    @Bean
    public FanoutExchange multipleLoginQueueExchange() {
        return new FanoutExchange(MULTIPLE_LOGIN_QUEUE_EXCHANGE);
    }

    @Bean
    public Queue multipleLoginQueue() {
        return new Queue(MULTIPLE_LOGIN_QUEUE);
    }

    @Bean
    public Binding multipleLoginQueueBinding() {
        return BindingBuilder.bind(multipleLoginQueue()).to(multipleLoginQueueExchange());
    }

}
