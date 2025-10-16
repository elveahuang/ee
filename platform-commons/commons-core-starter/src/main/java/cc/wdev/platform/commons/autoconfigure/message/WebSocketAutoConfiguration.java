package cc.wdev.platform.commons.autoconfigure.message;

import cc.wdev.platform.commons.autoconfigure.message.properties.WebSocketProperties;
import cc.wdev.platform.commons.message.socket.WebSocketSessionHandlerDecoratorFactory;
import cc.wdev.platform.commons.message.socket.handler.MessageWebSocketHandler;
import cc.wdev.platform.commons.message.socket.message.SocketMessageDelegate;
import cc.wdev.platform.commons.message.socket.message.SocketMessageListener;
import cc.wdev.platform.commons.message.socket.server.SessionHandshakeInterceptor;
import cc.wdev.platform.commons.message.socket.service.DefaultWebSocketService;
import cc.wdev.platform.commons.message.socket.service.DefaultWebSocketSessionService;
import cc.wdev.platform.commons.message.socket.service.WebSocketService;
import cc.wdev.platform.commons.message.socket.service.WebSocketSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author elvea
 */
@Slf4j
@EnableWebSocket
@AutoConfiguration
@EnableConfigurationProperties({WebSocketProperties.class})
@ConditionalOnClass(WebSocketSession.class)
@ConditionalOnProperty(prefix = WebSocketProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketAutoConfiguration {

    public WebSocketAutoConfiguration() {
        log.info("WebSocketAutoConfiguration is enabled.");
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
    public WebSocketService webSocketService(WebSocketSessionService webSocketSessionService) {
        return new DefaultWebSocketService(webSocketSessionService);
    }

}
