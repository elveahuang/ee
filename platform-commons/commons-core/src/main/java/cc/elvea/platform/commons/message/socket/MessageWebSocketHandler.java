package cc.elvea.platform.commons.message.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;

/**
 * @author elvea
 */
@Slf4j
@Component
public class MessageWebSocketHandler extends AbstractSessionWebSocketHandler {

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session,
                                     @NonNull TextMessage message) throws Exception {
        log.info("sending message......");
        session.sendMessage(new TextMessage(new Date().toString()));
    }

}
