package cc.wdev.platform.system.configuration;

import cc.wdev.platform.commons.message.socket.handler.MessageWebSocketHandler;
import cc.wdev.platform.commons.message.socket.server.SessionHandshakeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author elvea
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class SystemWebSocketConfiguration implements WebSocketConfigurer {

    private final MessageWebSocketHandler messageWebSocketHandler;

    private final SessionHandshakeInterceptor sessionHandshakeInterceptor;

    public SystemWebSocketConfiguration(MessageWebSocketHandler messageWebSocketHandler,
                                        SessionHandshakeInterceptor sessionHandshakeInterceptor) {
        log.info("SystemWebSocketConfiguration is enabled");
        this.messageWebSocketHandler = messageWebSocketHandler;
        this.sessionHandshakeInterceptor = sessionHandshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        // 技术验证
        log.info("Registry messageWebSocketHandler to path [{}]", "/ws/message");
        registry.addHandler(messageWebSocketHandler, "/ws/message")
            .setAllowedOrigins("*")
            .addInterceptors(sessionHandshakeInterceptor);

        // 在线聊天室
        log.info("Registry messageWebSocketHandler to path [{}]", "/ws/chat");
        registry.addHandler(messageWebSocketHandler, "/ws/chat")
            .setAllowedOrigins("*")
            .addInterceptors(sessionHandshakeInterceptor);
    }

}
