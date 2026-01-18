package cc.wdev.platform.system.config;

import cc.wdev.platform.system.commons.constants.SystemAmqpConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class AmqpConfiguration {

    /**
     * 替换默认转换器
     * 解决消息监听可能出现反序列化失败的问题
     */
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public Queue userSessionQueue() {
        return new Queue(SystemAmqpConstants.USER_SESSION);
    }

}
