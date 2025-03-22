package cc.elvea.platform.commons.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author elvea
 */
@Getter
public enum ResponseCodeEnum implements BaseEnum<Integer> {
    // 核心基础
    SUCCESS(HttpStatus.OK.value(), "Success", "正确执行并成功返回"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad Request", "错误的请求"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "未授权"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "Forbidden", "访问未授权"),
    NOT_FOUNT(HttpStatus.NOT_FOUND.value(), "Not Found", "请求地址不存在"),
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "System Error", "系统错误"),
    // 系统基础
    PARAM_ERROR(1000001, "Param Error", "参数检查不通过"),
    PARAM_NOT_PRESENT(1000002, "Param is not present", "参数不能为空"),
    INVALID_CAPTCHA(1000003, "Incorrect Captcha", "验证码错误或者验证码已经过期"),
    RATE_LIMIT_ERROR(1000004, "Rate Limit", "限流"),
    ATTACHMENT_LIMIT_ERROR(1000005, "Attachment Limit", "附件类型错误"),
    // 系统模块
    USER__USERNAME_NOT_AVAILABLE(1001001, "Username is Not Available", "用户名不可用"),
    USER__EMAIL_NOT_AVAILABLE(1001002, "E-Mail is Not Available", "邮箱不可用"),
    USER__MOBILE_NOT_AVAILABLE(1001003, "Mobile is not present.", "手机号码不可用"),
    USER__INVALID_USERNAME_OR_PASSWORD(1001004, "Invalid Username or Password.", "用户名或者密码不正确"),
    USER__INVITE_CODE_NOT_AVAILABLE(1001005, "Invitation code is Not Available.", "邀请码不可用"),
    USER__INVITE_CODE_LIMIT_REACHED(1001006, "Invitation limit reached.", "该邀请码邀请数上限");

    private final int code;
    private final String description;
    private final String message;

    ResponseCodeEnum(final int code, final String message, final String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return ("label_response_code__").concat(String.valueOf(this.code));
    }

}
