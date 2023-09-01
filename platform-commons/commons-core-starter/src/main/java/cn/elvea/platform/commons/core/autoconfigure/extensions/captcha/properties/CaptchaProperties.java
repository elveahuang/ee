package cn.elvea.platform.commons.core.autoconfigure.extensions.captcha.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CaptchaProperties.PREFIX)
public class CaptchaProperties {

    public static final String PREFIX = "platform.captcha";

    private Boolean enabled = Boolean.FALSE;

    private String cacheKeyPrefix = "captcha";

}
