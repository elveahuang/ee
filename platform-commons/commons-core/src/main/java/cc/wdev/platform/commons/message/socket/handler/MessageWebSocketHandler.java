package cc.wdev.platform.commons.message.socket.handler;

import cc.wdev.platform.commons.message.socket.AbstractSessionWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;

/**
 * @author elvea
 */
@Slf4j
public class MessageWebSocketHandler extends AbstractSessionWebSocketHandler {

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session,
                                     @NonNull TextMessage message) throws Exception {
        log.info("Sending message......");
        session.sendMessage(new TextMessage(new Date().toString()));
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session,
                                      @NonNull CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

}
