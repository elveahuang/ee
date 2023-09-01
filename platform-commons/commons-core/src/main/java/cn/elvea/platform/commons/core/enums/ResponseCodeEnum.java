package cn.elvea.platform.commons.core.enums;

import lombok.Getter;

/**
 * 语言枚举
 *
 * @author elvea
 * @since 0.0.1
 */
@Getter
public enum ResponseCodeEnum implements BaseEnum<String> {
    SUCCESS("200", "", "正确执行并成功返回"),
    ERROR("0", "", "系统错误"),
    UNAUTHORIZED("401", "", "未授权"),
    FORBIDDEN("403", "", "访问未授权"),
    NOT_FOUNT("404", "Not Found.", "请求地址不存在"),

    PARAM_ERROR("100001", "Param Error.", "参数检查不通过"),
    PARAM_NOT_PRESENT("100002", "Param is not present.", "参数不能为空");

    private final String code;
    private final String description;
    private final String message;

    ResponseCodeEnum(final String code, final String message, final String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return ("label_response_code__").concat(this.code.toLowerCase());
    }

}
