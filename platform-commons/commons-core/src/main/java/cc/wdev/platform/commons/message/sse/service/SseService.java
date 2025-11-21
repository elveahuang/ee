package cc.wdev.platform.commons.message.sse.service;

import cc.wdev.platform.commons.message.sse.message.SseRequestMsg;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author elvea
 */
public interface SseService {

    /**
     * 处理消息
     */
    void handle(SseRequestMsg requestMsg) throws Exception;

    /**
     * 注册会话
     */
    SseEmitter connect(String sessionId, String userId);
}
