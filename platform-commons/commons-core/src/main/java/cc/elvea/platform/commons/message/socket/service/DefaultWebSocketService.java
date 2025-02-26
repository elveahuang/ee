package cc.elvea.platform.commons.message.socket.service;

import cc.elvea.platform.commons.message.socket.WebSocketUserSession;
import cc.elvea.platform.commons.message.socket.message.SocketMessage;
import cc.elvea.platform.commons.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

import static cc.elvea.platform.commons.message.socket.WebSocketConstants.SOCKET_USER_SESSION_KEY;
import static cc.elvea.platform.commons.message.socket.WebSocketConstants.USER_MESSAGE_ENDPOINT;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class DefaultWebSocketService implements WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    private final WebSocketSessionService webSocketSessionService;

    /**
     * @see WebSocketService#handleSocketMessage(SocketMessage)
     */
    public void handleSocketMessage(SocketMessage message) {
        // 检查是否存在用户会话，如果存在则直接推送消息
        String userSessionId = message.getSessionId();
        Map<String, WebSocketSession> sessionMap = this.webSocketSessionService.getWsSession(userSessionId);
        if (!CollectionUtils.isEmpty(sessionMap)) {
            this.messagingTemplate.convertAndSendToUser(userSessionId, USER_MESSAGE_ENDPOINT, message);
        }
    }

    /**
     * @see WebSocketService#afterConnectionEstablished(WebSocketSession)
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession wsSession) {
        WebSocketUserSession wsUserSession = (WebSocketUserSession) wsSession.getAttributes().get(SOCKET_USER_SESSION_KEY);
        log.info("Create User Socket Session [{}] for user session [{}].", wsSession.getId(), wsUserSession.getSessionId());
        webSocketSessionService.createWebSocketSession(wsSession, wsUserSession);
    }

    /**
     * @see WebSocketService#afterConnectionClosed(WebSocketSession, CloseStatus)
     */
    @Override
    public void afterConnectionClosed(WebSocketSession wsSession, CloseStatus closeStatus) {
        WebSocketUserSession wsUserSession = (WebSocketUserSession) wsSession.getAttributes().get(SOCKET_USER_SESSION_KEY);
        log.info("Close User Socket Session [{}] for user session [{}].", wsSession.getId(), wsUserSession.getSessionId());
        webSocketSessionService.closeWebSocketSession(wsSession, wsUserSession);
    }

}
