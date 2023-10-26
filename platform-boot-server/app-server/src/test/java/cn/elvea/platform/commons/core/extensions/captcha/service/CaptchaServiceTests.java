package cn.elvea.platform.commons.core.extensions.captcha.service;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaCheckRequest;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class CaptchaServiceTests extends BaseTests {

    @Autowired
    private CaptchaService captchaService;

    @Test
    public void base() throws Exception {
        Assertions.assertNotNull(this.captchaService);
    }

    @Test
    public void codeCaptchaTest() throws Exception {
        Assertions.assertNotNull(this.captchaService);

        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.CODE).build();
        Captcha captcha = this.captchaService.generate(request);

        CaptchaCheckRequest checkRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.CODE)
                .key(captcha.getKey())
                .value(captcha.getValue())
                .build();
        boolean result = this.captchaService.check(checkRequest);

        Assertions.assertTrue(result);
    }

    @Test
    public void mailCaptchaTest() throws Exception {
        Assertions.assertNotNull(this.captchaService);

        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.MAIL).email("huang@elvea.cn").build();
        Captcha captcha = this.captchaService.generate(request);

        CaptchaCheckRequest checkRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(captcha.getEmail())
                .key(captcha.getKey())
                .value(captcha.getValue())
                .build();
        boolean result = this.captchaService.check(checkRequest);

        Assertions.assertTrue(result);
    }

    @Test
    public void smsCaptchaTest() throws Exception {
        Assertions.assertNotNull(this.captchaService);

        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.SMS).mobileNumber("13800138000").build();
        Captcha captcha = this.captchaService.generate(request);

        CaptchaCheckRequest checkRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(captcha.getEmail())
                .key(captcha.getKey())
                .value(captcha.getValue())
                .build();
        boolean result = this.captchaService.check(checkRequest);

        Assertions.assertTrue(result);
    }

}
