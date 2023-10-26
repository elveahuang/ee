package cn.elvea.platform.commons.core.extensions.captcha.request;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaCheckRequest implements Serializable {
    private CaptchaTypeEnum type;
    private String email;
    private String mobileCountryCode;
    private String mobileNumber;
    private String key;
    private String value;
    // 是否在校验后删除验证码
    @Builder.Default
    private boolean clearAfterCheck = false;
}
