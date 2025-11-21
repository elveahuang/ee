package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SseResponseCodeEnum implements BaseBizTypeEnum<Integer> {
    // 核心基础
    SUCCESS(HttpStatus.OK.value(), "Success", "正确执行并成功返回"),
    COMPLETED(HttpStatus.OK.value(), "Completed", "正确执行完成"),
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", "系统错误"),

    ;

    private final Integer value;
    private final String description;
    private final String message;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.SSE_RESPONSE_CODE.getValue().toUpperCase();
    }

}
