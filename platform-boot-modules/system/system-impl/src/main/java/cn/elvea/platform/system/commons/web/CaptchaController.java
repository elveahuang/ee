package cn.elvea.platform.system.commons.web;

import cn.elvea.platform.commons.core.annotations.OperationLog;
import cn.elvea.platform.commons.core.annotations.RateLimit;
import cn.elvea.platform.commons.core.enums.CaptchaTypeEnum;
import cn.elvea.platform.commons.core.extensions.captcha.Captcha;
import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaCodeDto;
import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaDto;
import cn.elvea.platform.commons.core.extensions.captcha.request.CaptchaRequest;
import cn.elvea.platform.commons.core.web.R;
import cn.elvea.platform.commons.core.web.controller.AbstractController;
import cn.elvea.platform.system.commons.api.CaptchaApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

import static cn.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@RestController
@AllArgsConstructor
@Tag(name = "CaptchaController", description = "验证码控制器")
public class CaptchaController extends AbstractController {

    private final CaptchaApi captchaApi;

    @OperationLog("获取验证码")
    @Operation(summary = "获取验证码")
    @ApiResponse(description = "获取验证码")
    @PostMapping(API_V1_CAPTCHA_CODE)
    public R<CaptchaCodeDto> captchaCode() throws Exception {
        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.CODE).duration(Duration.ofMinutes(1)).build();
        Captcha captcha = this.captchaApi.generate(request);
        return R.success(CaptchaCodeDto.builder().key(captcha.getKey()).image(captcha.getImage()).build());
    }

    @OperationLog("获取邮件验证码")
    @Operation(summary = "获取邮件验证码")
    @ApiResponse(description = "获取邮件验证码")
    @PostMapping(API_V1_CAPTCHA_MAIL)
    public R<CaptchaDto> captchaEmail(@RequestParam String email) throws Exception {
        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.MAIL).email(email).duration(Duration.ofMinutes(5)).build();
        Captcha captcha = this.captchaApi.generate(request);
        return R.success(CaptchaDto.builder().key(captcha.getKey()).build());
    }

    @OperationLog("获取手机验证码")
    @Operation(summary = "获取手机验证码")
    @ApiResponse(description = "获取手机验证码")
    @PostMapping(API_V1_CAPTCHA_SMS)
    @RateLimit
    public R<CaptchaDto> captchaMobile(@RequestParam String mobileCountryCode, @RequestParam String mobileNumber) throws Exception {
        CaptchaRequest request = CaptchaRequest.builder().type(CaptchaTypeEnum.SMS)
                .mobileCountryCode(mobileCountryCode).mobileNumber(mobileNumber).duration(Duration.ofMinutes(1)).build();
        Captcha captcha = this.captchaApi.generate(request);
        return R.success(CaptchaDto.builder().key(captcha.getKey()).build());
    }

}
