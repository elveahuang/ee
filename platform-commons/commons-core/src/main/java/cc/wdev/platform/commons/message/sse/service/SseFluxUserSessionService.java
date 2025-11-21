package cc.wdev.platform.commons.message.sse.service;

import cc.wdev.platform.commons.message.sse.message.SseResponseMsg;
import reactor.core.publisher.Sinks;

import java.util.Map;

public interface SseFluxUserSessionService<T> {
    /**
     * 创建会话
     */
    Sinks.Many<SseResponseMsg<String>> createSink(String key, String sessionId);

    /**
     * 获取会话
     */
    Map<String, Sinks.Many<SseResponseMsg<String>>> getSinks(String key);

    /**
     * 删除会话
     */
    void removeSink(String key);

    /**
     * 删除会话
     */
    void removeSink(String key, String sessionId);

    /**
     * 是否存在会话
     */
    boolean hasSink(String key);
}
