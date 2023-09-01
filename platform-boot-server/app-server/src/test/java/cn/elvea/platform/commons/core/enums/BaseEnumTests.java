package cn.elvea.platform.commons.core.enums;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class BaseEnumTests {

    @Test
    public void base() {
        CaptchaTypeEnum captchaTypeEnum = BaseEnum.getEnumByValue("SMS", CaptchaTypeEnum.class);
        Assertions.assertNotNull(captchaTypeEnum);
    }

}
