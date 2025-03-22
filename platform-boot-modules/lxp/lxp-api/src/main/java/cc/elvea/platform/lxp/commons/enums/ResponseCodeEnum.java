package cc.elvea.platform.lxp.commons.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum ResponseCodeEnum implements BaseEnum<String> {
    // 用户模块
    USER__USERNAME_NOT_AVAILABLE("20010001", "Param is not present.", "参数不能为空"),
    USER__EMAIL_NOT_AVAILABLE("20010002", "Param is not present.", "参数不能为空"),
    USER__MOBILE_NOT_AVAILABLE("20010003", "Param is not present.", "参数不能为空");

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
