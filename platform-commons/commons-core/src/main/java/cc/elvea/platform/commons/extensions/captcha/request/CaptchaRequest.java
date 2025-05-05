package cc.elvea.platform.commons.extensions.captcha.request;

import cc.elvea.platform.commons.enums.CaptchaTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Duration;

/**
 * @author elvea
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
    @Builder.Default
    private Duration duration = Duration.ofMinutes(5);
}
