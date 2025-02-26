package cc.elvea.platform.commons.message.socket.service;

import cc.elvea.platform.commons.message.socket.WebSocketUserSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collection;
import java.util.Map;

/**
 * @author elvea
 */
public interface WebSocketSessionService {

    /**
     * 创建会话
     *
     * @param webSocketSession     {@link WebSocketSession}
     * @param webSocketUserSession {@link WebSocketUserSession}
     */
    void createWebSocketSession(WebSocketSession webSocketSession, WebSocketUserSession webSocketUserSession);

    /**
     * 结束会话
     *
     * @param webSocketSession     {@link WebSocketSession}
     * @param webSocketUserSession {@link WebSocketUserSession}
     */
    void closeWebSocketSession(WebSocketSession webSocketSession, WebSocketUserSession webSocketUserSession);

    /**
     * 获取会话
     *
     * @param userSessionId 用户会话ID
     * @return 返回当前用户登录会话所有的WebSocket会话记录
     */
    Map<String, WebSocketSession> getWsSession(String userSessionId);

    /**
     * 获取所有用户会话
     *
     * @return 返回所有用户会话
     */
    Collection<Map<String, WebSocketSession>> getWsSessions();

    /**
     * 获取所有用户会话
     *
     * @return 返回所有用户会话
     */
    Map<String, Map<String, WebSocketSession>> getWsSessionMap();

}
