package cc.elvea.platform.commons.message.socket.service;

import cc.elvea.platform.commons.message.socket.message.SocketMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author elvea
 */
public interface WebSocketService {

    /**
     * 处理消息
     */
    void handleSocketMessage(SocketMessage message);

    /**
     * 握手成功后，注册会话
     */
    void afterConnectionEstablished(WebSocketSession wsSession);

    /**
     * 注销会话
     */
    void afterConnectionClosed(WebSocketSession wsSession, CloseStatus closeStatus);

}
