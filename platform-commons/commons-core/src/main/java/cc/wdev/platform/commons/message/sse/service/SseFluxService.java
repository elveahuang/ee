package cc.wdev.platform.commons.message.sse.service;

import cc.wdev.platform.commons.message.sse.message.SseRequestMsg;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface SseFluxService<T> {
    /**
     * 处理消息
     */
    void handle(SseRequestMsg requestMsg);

    /**
     * 注册会话
     */
    Flux<ServerSentEvent<T>> connect(String sessionId, String userId);
}
