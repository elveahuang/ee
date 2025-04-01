package cc.elvea.platform.commons.autoconfigure.message;

import cc.elvea.platform.commons.autoconfigure.message.properties.WebSocketProperties;
import cc.elvea.platform.commons.message.socket.WebSocketSessionHandlerDecoratorFactory;
import cc.elvea.platform.commons.message.socket.server.SessionHandshakeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * @author elvea
 */
@Slf4j
@EnableWebSocketMessageBroker
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WebSocketProperties.class})
@ConditionalOnProperty(prefix = WebSocketProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketMessageBrokerAutoConfiguration implements WebSocketMessageBrokerConfigurer {

    private final WebSocketSessionHandlerDecoratorFactory sessionHandlerDecoratorFactory;

    private final SessionHandshakeInterceptor sessionHandshakeInterceptor;

    public WebSocketMessageBrokerAutoConfiguration(WebSocketSessionHandlerDecoratorFactory sessionHandlerDecoratorFactory,
                                                   SessionHandshakeInterceptor sessionHandshakeInterceptor) {
        log.info("WebSocketMessageBrokerAutoConfiguration is enabled.");
        this.sessionHandlerDecoratorFactory = sessionHandlerDecoratorFactory;
        this.sessionHandshakeInterceptor = sessionHandshakeInterceptor;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/web-socket")
                .setAllowedOrigins("*")
                .addInterceptors(this.sessionHandshakeInterceptor);

        registry.addEndpoint("/web-socket/sock-js")
                .setAllowedOrigins("*")
                .addInterceptors(this.sessionHandshakeInterceptor)
                .withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.addDecoratorFactory(sessionHandlerDecoratorFactory);
    }

}
