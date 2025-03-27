package cc.elvea.platform.commons.extensions.captcha;

import cc.elvea.platform.commons.enums.CaptchaTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Captcha implements Serializable {
    private CaptchaTypeEnum type;
    private String key;
    private String value;
    private String image;
    private String email;
    private String mobileCountryCode;
    private String mobileNumber;
}
