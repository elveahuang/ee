package cc.elvea.platform.commons.message.socket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
public class WebSocketUserSession implements Serializable {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户登录会话ID
     */
    private String sessionId;
    /**
     * 用户所有WebSocket会话记录
     */
    private Map<String, String> sessionMap = new ConcurrentHashMap<>();
}
