package cc.elvea.platform.commons.message.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * @author elvea
 */
@Slf4j
public class WebSocketSessionHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    @Override
    @NonNull
    public WebSocketHandler decorate(@NonNull WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
                super.afterConnectionEstablished(session);
            }

            @Override
            public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
                super.afterConnectionClosed(session, status);
            }
        };
    }

}
