package cc.wdev.platform.commons.autoconfigure.message;

import cc.wdev.platform.commons.autoconfigure.message.properties.WebSocketProperties;
import cc.wdev.platform.commons.message.socket.handler.MessageWebSocketHandler;
import cc.wdev.platform.commons.message.socket.server.SessionHandshakeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author elvea
 */
@Slf4j
@EnableWebSocket
@EnableConfigurationProperties({WebSocketProperties.class})
@ConditionalOnClass({WebSocketConfigurer.class})
@ConditionalOnProperty(prefix = WebSocketProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketAutoConfiguration implements WebSocketConfigurer {

    private final MessageWebSocketHandler messageWebSocketHandler;

    private final SessionHandshakeInterceptor sessionHandshakeInterceptor;

    public WebSocketAutoConfiguration(MessageWebSocketHandler messageWebSocketHandler,
                                      SessionHandshakeInterceptor sessionHandshakeInterceptor) {
        log.info("WebSocketAutoConfiguration is enabled");
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
