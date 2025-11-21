package cc.wdev.platform.commons.message.sse.message;

import cc.wdev.platform.commons.enums.SseResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SseResponseMsg<T> {

    Integer code;

    String message;

    private T payload;

    private String conversationId;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> SseResponseMsg<T> success(T payload) {
        return SseResponseMsg.<T>builder()
            .code(SseResponseCodeEnum.SUCCESS.getValue())
            .message(SseResponseCodeEnum.SUCCESS.getDescription())
            .payload(payload)
            .build();
    }

    public static <T> SseResponseMsg<T> success(T payload, String conversationId) {
        return SseResponseMsg.<T>builder()
            .code(SseResponseCodeEnum.SUCCESS.getValue())
            .message(SseResponseCodeEnum.SUCCESS.getDescription())
            .payload(payload)
            .conversationId(conversationId)
            .build();
    }

    public static <T> SseResponseMsg<T> completed(String conversationId) {
        return SseResponseMsg.<T>builder()
            .code(SseResponseCodeEnum.COMPLETED.getValue())
            .message(SseResponseCodeEnum.COMPLETED.getDescription())
            .conversationId(conversationId)
            .build();
    }

    public static <T> SseResponseMsg<T> error(String message, String conversationId) {
        return SseResponseMsg.<T>builder()
            .code(SseResponseCodeEnum.ERROR.getValue())
            .message(message)
            .conversationId(conversationId)
            .build();
    }
}
