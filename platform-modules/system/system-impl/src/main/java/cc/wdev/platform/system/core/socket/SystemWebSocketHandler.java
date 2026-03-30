package cc.wdev.platform.system.core.socket;

import cc.wdev.platform.commons.message.model.SimpleJsonMessage;
import cc.wdev.platform.commons.message.model.SimpleTextMessage;
import cc.wdev.platform.commons.message.session.UserSession;
import cc.wdev.platform.commons.message.socket.servlet.ServletWebSocketManager;
import cc.wdev.platform.commons.message.socket.servlet.handler.AbstractWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

import static cc.wdev.platform.commons.message.socket.WebSocketConstants.SOCKET_USER_SESSION_KEY;

/**
 * @author elvea
 */
@Slf4j
public class SystemWebSocketHandler extends AbstractWebSocketHandler {

    private final ServletWebSocketManager servletWebSocketManager;

    private final SystemWebSocketService systemWebSocketService;

    public SystemWebSocketHandler(ServletWebSocketManager servletWebSocketManager, SystemWebSocketService systemWebSocketService) {
        this.servletWebSocketManager = servletWebSocketManager;
        this.systemWebSocketService = systemWebSocketService;
    }

    /**
     * @see AbstractWebSocketHandler#getWebSocketManager()
     */
    @Override
    protected ServletWebSocketManager getWebSocketManager() {
        return this.servletWebSocketManager;
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session,
                                     @NonNull TextMessage message) throws Exception {
        UserSession wsUserSession = (UserSession) session.getAttributes().get(SOCKET_USER_SESSION_KEY);
        log.info("Create User Socket Session [{}] for user session [{}].", session.getId(), wsUserSession.getSid());

        log.info("Sending message......");
        SimpleTextMessage m = new SimpleTextMessage();
        m.setKeys(List.of(session.getId()));
        m.setContent("Hello World");
        systemWebSocketService.publish(m);

        log.info("Sending message......");
        SimpleJsonMessage<UserSession> jm = new SimpleJsonMessage<>();
        jm.setKeys(List.of(session.getId()));
        jm.setContent(wsUserSession);
        systemWebSocketService.publish(jm);
    }

}
