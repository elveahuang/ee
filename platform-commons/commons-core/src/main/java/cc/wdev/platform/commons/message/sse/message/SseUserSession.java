package cc.wdev.platform.commons.message.sse.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SseUserSession {
    private String userId;
    private String sessionId;
    private SseEmitter emitter;
}

