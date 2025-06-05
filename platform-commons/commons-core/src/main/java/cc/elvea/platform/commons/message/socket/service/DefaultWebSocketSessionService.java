package cc.elvea.platform.commons.message.socket.service;

import cc.elvea.platform.commons.message.socket.WebSocketUserSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class DefaultWebSocketSessionService implements WebSocketSessionService {

    /**
     * 保存当前应用节点所有的会话记录
     */
    private final ConcurrentHashMap<String, Map<String, WebSocketSession>> sessionMap = new ConcurrentHashMap<>();

    /**
     * @see WebSocketSessionService#createWebSocketSession(WebSocketSession, WebSocketUserSession)
     */
    @Override
    public void createWebSocketSession(WebSocketSession webSocketSession, WebSocketUserSession webSocketUserSession) {
        String userSessionId = webSocketUserSession.getSessionId();

        Map<String, WebSocketSession> sessions = this.sessionMap.get(userSessionId);
        if (sessions == null) {
            sessions = new ConcurrentHashMap<>();
            this.sessionMap.putIfAbsent(userSessionId, sessions);
            sessions = this.sessionMap.get(userSessionId);
        }
        sessions.put(webSocketSession.getId(), webSocketSession);
    }

    /**
     * @see WebSocketSessionService#closeWebSocketSession(WebSocketSession, WebSocketUserSession)
     */
    @Override
    public void closeWebSocketSession(WebSocketSession webSocketSession, WebSocketUserSession webSocketUserSession) {
        String webSocketSessionId = webSocketSession.getId();
        String webSocketUserSessionId = webSocketUserSession.getSessionId();

        Map<String, WebSocketSession> sessions = this.sessionMap.get(webSocketUserSessionId);
        if (sessions != null) {
            boolean result = sessions.remove(webSocketSession.getId()) != null;
            log.info("Close Web Socket Session [{}] [{}] - {}.", webSocketUserSessionId, webSocketSessionId, result);
            if (sessions.isEmpty()) {
                this.sessionMap.remove(webSocketUserSessionId);
                log.info("Close all Web Socket Session [{}].", webSocketUserSessionId);
            }
        }
    }

    /**
     * @see WebSocketSessionService#getWsSession(String)
     */
    @Override
    public Map<String, WebSocketSession> getWsSession(String userSessionId) {
        return this.sessionMap.get(userSessionId);
    }

    /**
     * @see WebSocketSessionService#getWsSessions()
     */
    @Override
    public Collection<Map<String, WebSocketSession>> getWsSessions() {
        return this.sessionMap.values();
    }

    /**
     * @see WebSocketSessionService#getWsSessionMap()
     */
    @Override
    public Map<String, Map<String, WebSocketSession>> getWsSessionMap() {
        return this.sessionMap;
    }

}
