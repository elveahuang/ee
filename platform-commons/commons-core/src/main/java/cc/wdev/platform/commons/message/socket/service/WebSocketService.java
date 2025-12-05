package cc.wdev.platform.commons.message.socket.service;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author elvea
 */
public interface WebSocketService<T> {

    /**
     * 处理消息
     */
    void handleSocketMessage(T message) throws Exception;

    /**
     * 握手成功后，注册会话
     */
    void afterConnectionEstablished(WebSocketSession wsSession);

    /**
     * 注销会话
     */
    void afterConnectionClosed(WebSocketSession wsSession, CloseStatus closeStatus);

}
