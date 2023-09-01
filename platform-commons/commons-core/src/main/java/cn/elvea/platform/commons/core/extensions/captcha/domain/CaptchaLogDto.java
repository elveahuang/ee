package cn.elvea.platform.commons.core.extensions.captcha.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CaptchaLogDto implements Serializable {
    /**
     * 验证码类型
     */
    private String captchaType;
    /**
     * 验证码标识
     */
    private String captchaKey;
    /**
     * 验证码
     */
    private String captchaValue;
    /**
     * 接收人
     */
    private String email;
    private String mobileCountryCode;
    private String mobileNumber;
}
