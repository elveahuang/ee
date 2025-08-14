package top.baihu.platform.commons.extensions.captcha;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.baihu.platform.commons.enums.CaptchaTypeEnum;

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
