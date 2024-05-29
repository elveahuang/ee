package cc.elvea.platform.commons.extensions.captcha.request;

import cc.elvea.platform.commons.enums.CaptchaTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
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
}
