package cc.elvea.platform.commons.autoconfigure.message;

import cc.elvea.platform.commons.autoconfigure.message.properties.WebSocketProperties;
import cc.elvea.platform.commons.message.socket.WebSocketSessionHandlerDecoratorFactory;
import cc.elvea.platform.commons.message.socket.handler.MessageWebSocketHandler;
import cc.elvea.platform.commons.message.socket.message.SocketMessageDelegate;
import cc.elvea.platform.commons.message.socket.message.SocketMessageListener;
import cc.elvea.platform.commons.message.socket.server.SessionHandshakeInterceptor;
import cc.elvea.platform.commons.message.socket.service.DefaultWebSocketService;
import cc.elvea.platform.commons.message.socket.service.DefaultWebSocketSessionService;
import cc.elvea.platform.commons.message.socket.service.WebSocketService;
import cc.elvea.platform.commons.message.socket.service.WebSocketSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WebSocketProperties.class})
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
