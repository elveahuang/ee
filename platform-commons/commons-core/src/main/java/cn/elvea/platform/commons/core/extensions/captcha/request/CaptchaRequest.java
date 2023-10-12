package cn.elvea.platform.commons.core.extensions.captcha.request;

import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaRequest implements Serializable {
    private CaptchaTypeEnum type;
    private String email;
    private String mobileCountryCode;
    private String mobileNumber;
    @Builder.Default
    private int size = 6;
    private Duration duration;
}
