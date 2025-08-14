package top.baihu.platform.commons.autoconfigure.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import top.baihu.platform.commons.autoconfigure.message.properties.WebSocketProperties;
import top.baihu.platform.commons.message.socket.WebSocketSessionHandlerDecoratorFactory;
import top.baihu.platform.commons.message.socket.handler.MessageWebSocketHandler;
import top.baihu.platform.commons.message.socket.message.SocketMessageDelegate;
import top.baihu.platform.commons.message.socket.message.SocketMessageListener;
import top.baihu.platform.commons.message.socket.server.SessionHandshakeInterceptor;
import top.baihu.platform.commons.message.socket.service.DefaultWebSocketService;
import top.baihu.platform.commons.message.socket.service.DefaultWebSocketSessionService;
import top.baihu.platform.commons.message.socket.service.WebSocketService;
import top.baihu.platform.commons.message.socket.service.WebSocketSessionService;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WebSocketProperties.class})
@ConditionalOnClass(WebSocketHandlerDecoratorFactory.class)
@ConditionalOnProperty(prefix = WebSocketProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketCommonAutoConfiguration {

    public WebSocketCommonAutoConfiguration(WebSocketProperties properties) {
        log.info("WebSocketCommonAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketSessionHandlerDecoratorFactory webSocketSessionHandlerDecoratorFactory() {
        return new WebSocketSessionHandlerDecoratorFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageWebSocketHandler messageWebSocketHandler() {
        return new MessageWebSocketHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public SessionHandshakeInterceptor sessionHandshakeInterceptor() {
        return new SessionHandshakeInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public SocketMessageDelegate socketMessageDelegate() {
        return new SocketMessageDelegate();
    }

    @Bean
    @ConditionalOnMissingBean
    public SocketMessageListener socketMessageListener() {
        return new SocketMessageListener();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketSessionService webSocketSessionService() {
        return new DefaultWebSocketSessionService();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketService webSocketService(SimpMessagingTemplate messagingTemplate,
                                             WebSocketSessionService webSocketSessionService) {
        return new DefaultWebSocketService(messagingTemplate, webSocketSessionService);
    }

}
