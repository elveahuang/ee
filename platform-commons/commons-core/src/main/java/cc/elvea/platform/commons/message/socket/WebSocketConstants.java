package cc.elvea.platform.commons.message.socket;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface WebSocketConstants {

    String WEB_SOCKET_SESSION_CACHE = "WebSocketConstants.WEB_SOCKET_SESSION_CACHE";

    String SOCKET_USER_SESSION_KEY = "WebSocketConstants.SOCKET_USER_SESSION_KEY";

    /**
     * 用户点对点断点
     */
    String USER_MESSAGE_ENDPOINT = "/message";

    /**
     * 全局断点
     */
    String TOPIC_MESSAGE_ENDPOINT = "/topic/message";

}
