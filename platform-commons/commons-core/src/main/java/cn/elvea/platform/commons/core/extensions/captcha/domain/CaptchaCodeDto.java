package cn.elvea.platform.commons.core.extensions.captcha.domain;

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
public class CaptchaCodeDto implements Serializable {
    private String key;
    private String image;
}
