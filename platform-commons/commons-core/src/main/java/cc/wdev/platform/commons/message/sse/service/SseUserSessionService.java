package cc.wdev.platform.commons.message.sse.service;

import cc.wdev.platform.commons.message.sse.message.SseUserSession;

import java.util.Map;

public interface SseUserSessionService {

    void createSession(String key, SseUserSession session);

    Map<String, SseUserSession> getSessions(String key);

    void removeSession(String key);

    void removeSession(String key, String sessionId);

    void sendHeartbeat();
}
