package cn.elvea.platform.commons.core.extensions.captcha;

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
@AllArgsConstructor
@NoArgsConstructor
public class Captcha implements Serializable {
    private CaptchaTypeEnum type;
    private String key;
    private String value;
    private String image;
}
