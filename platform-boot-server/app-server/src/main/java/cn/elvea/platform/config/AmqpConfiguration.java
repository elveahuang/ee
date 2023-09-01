package cn.elvea.platform.config;

import cn.elvea.platform.system.commons.constants.SystemAmqpConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class AmqpConfiguration {

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
        return new Queue(SystemAmqpConstants.USER_SESSION);
    }

    @Bean
    public Queue captchaLogQueue() {
        return new Queue(SystemAmqpConstants.CAPTCHA_LOG_QUEUE);
    }

    @Bean
    public Queue operationLogQueue() {
        return new Queue(SystemAmqpConstants.OPERATION_LOG_QUEUE);
    }

}
