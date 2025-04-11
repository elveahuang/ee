package cc.elvea.platform.system.commons.controller;

import cc.elvea.platform.commons.annotations.Anonymous;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.annotations.RateLimiter;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.enums.CaptchaTypeEnum;
import cc.elvea.platform.commons.extensions.captcha.Captcha;
import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaCodeDto;
import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaDto;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaRequest;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.commons.api.CaptchaApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "CaptchaController", description = "验证码控制器")
public class CaptchaController extends AbstractController {

    private final CaptchaApi captchaApi;

    @Anonymous
    @RateLimiter
    @OperationLog("获取验证码")
    @Operation(summary = "获取验证码")
    @ApiResponse(description = "获取验证码")
    @PostMapping(API_V1_CAPTCHA_CODE)
    public R<CaptchaCodeDto> captchaCode() throws Exception {
        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.CODE).size(4).build();
        Captcha captcha = this.captchaApi.generate(request);
        return R.success(CaptchaCodeDto.builder().key(captcha.getKey()).image(captcha.getImage()).build());
    }

    @Anonymous
    @RateLimiter
    @OperationLog("获取邮件验证码")
    @Operation(summary = "获取邮件验证码")
    @ApiResponse(description = "获取邮件验证码")
    @PostMapping(API_V1_CAPTCHA_MAIL)
    public R<CaptchaDto> captchaEmail(@RequestParam String email) throws Exception {
        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.MAIL).size(6).email(email).build();
        Captcha captcha = this.captchaApi.generate(request);
        return R.success(CaptchaDto.builder().key(captcha.getKey()).build());
    }

    @Anonymous
    @RateLimiter
    @OperationLog("获取手机验证码")
    @Operation(summary = "获取手机验证码")
    @ApiResponse(description = "获取手机验证码")
    @PostMapping(API_V1_CAPTCHA_SMS)
    public R<CaptchaDto> captchaMobile(@RequestParam String mobileCountryCode, @RequestParam String mobileNumber) throws Exception {
        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.SMS).mobileCountryCode(mobileCountryCode).mobileNumber(mobileNumber).size(6).build();
        Captcha captcha = this.captchaApi.generate(request);
        return R.success(CaptchaDto.builder().key(captcha.getKey()).build());
    }

}
