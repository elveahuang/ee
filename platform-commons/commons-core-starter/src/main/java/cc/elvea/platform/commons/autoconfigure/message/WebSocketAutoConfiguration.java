package cc.elvea.platform.commons.autoconfigure.message;

import cc.elvea.platform.commons.autoconfigure.message.properties.WebSocketProperties;
import cc.elvea.platform.commons.message.socket.handler.MessageWebSocketHandler;
import cc.elvea.platform.commons.message.socket.server.SessionHandshakeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author elvea
 */
@Slf4j
@EnableWebSocket
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WebSocketProperties.class})
@ConditionalOnProperty(prefix = WebSocketProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketAutoConfiguration implements WebSocketConfigurer {

    private final MessageWebSocketHandler messageWebSocketHandler;

    private final SessionHandshakeInterceptor sessionHandshakeInterceptor;

    public WebSocketAutoConfiguration(MessageWebSocketHandler messageWebSocketHandler,
                                      SessionHandshakeInterceptor sessionHandshakeInterceptor) {
        log.info("WebSocketAutoConfiguration is enabled.");
        this.messageWebSocketHandler = messageWebSocketHandler;
        this.sessionHandshakeInterceptor = sessionHandshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(messageWebSocketHandler, "/socket/message")
                .setAllowedOrigins("*")
                .addInterceptors(sessionHandshakeInterceptor);
    }

}
