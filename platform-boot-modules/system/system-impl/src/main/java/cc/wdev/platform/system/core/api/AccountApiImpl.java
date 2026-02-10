package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.enums.ActionTypeEnum;
import cc.wdev.platform.commons.enums.CaptchaTypeEnum;
import cc.wdev.platform.commons.enums.ResponseCodeEnum;
import cc.wdev.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import cc.wdev.platform.commons.extensions.captcha.service.CaptchaService;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.SecurityUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.commons.enums.EntityTypeEnum;
import cc.wdev.platform.system.core.domain.converter.AccountConverter;
import cc.wdev.platform.system.core.domain.dto.AccountForgotPasswordDto;
import cc.wdev.platform.system.core.domain.dto.AccountInfoDto;
import cc.wdev.platform.system.core.domain.dto.AccountLoginDto;
import cc.wdev.platform.system.core.domain.dto.LoginSessionDto;
import cc.wdev.platform.system.core.domain.entity.AccountEntity;
import cc.wdev.platform.system.core.domain.form.*;
import cc.wdev.platform.system.core.domain.request.AccountCheckRequest;
import cc.wdev.platform.system.core.service.AccountService;
import cc.wdev.platform.system.core.service.LoginSessionRabbitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountApiImpl implements AccountApi {

    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    private final CaptchaService captchaService;

    private final LoginSessionRabbitService loginSessionRabbitService;

    /**
     * @see AccountApi#check(AccountCheckRequest)
     */
    @Override
    public boolean check(AccountCheckRequest request) {
        return this.accountService.check(request);
    }

    /**
     * @see AccountApi#register(AccountRegisterForm)
     */
    @Override
    public R<?> register(AccountRegisterForm form) {
        // 检测验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
            .type(CaptchaTypeEnum.EMAIL)
            .email(form.getEmail())
            .key(form.getCaptchaKey())
            .value(form.getCaptchaValue())
            .build();
        if (!captchaService.validate(captchaCheckRequest)) {
            return R.error();
        }
        // 检测用户名是否可用
        AccountCheckRequest usernameCheckRequest = AccountCheckRequest.builder().username(form.getUsername()).build();
        if (!accountService.check(usernameCheckRequest)) {
            return R.fail(ResponseCodeEnum.USER__USERNAME_NOT_AVAILABLE);
        }
        // 检测邮箱是否可用
        AccountCheckRequest emailCheckRequest = AccountCheckRequest.builder().email(form.getEmail()).build();
        if (!accountService.check(emailCheckRequest)) {
            return R.fail(ResponseCodeEnum.USER__EMAIL_NOT_AVAILABLE);
        }
        // 保存账号
        AccountEntity entity = AccountEntity.builder()
            .username(form.getUsername())
            .displayName(form.getUsername())
            .name(form.getUsername())
            .password(passwordEncoder.encode(form.getPassword()))
            .email(form.getEmail())
            .build();
        accountService.save(entity);
        return R.success();
    }

    /**
     * @see AccountApi#getUserInfo(String)
     */
    @Override
    public AccountInfoDto getUserInfo(String username) {
        AccountEntity entity = accountService.findByUsername(username);
        return getInfoDto(entity);
    }

    /**
     * @see AccountApi#findByUsername(String)
     */
    @Override
    public AccountLoginDto findByUsername(String username) {
        AccountEntity entity = accountService.findByUsername(username);
        return getLoginInfoDto(entity);
    }

    /**
     * @see AccountApi#findByMobile(String, String)
     */
    @Override
    public AccountLoginDto findByMobile(String mobileCountryCode, String mobileNumber) {
        AccountEntity entity = accountService.findByMobile(mobileCountryCode, mobileNumber);
        return getLoginInfoDto(entity);
    }

    /**
     * @see AccountApi#findByEmail(String)
     */
    @Override
    public AccountLoginDto findByEmail(String email) {
        AccountEntity entity = accountService.findByEmail(email);
        return getLoginInfoDto(entity);
    }

    /**
     * @see AccountApi#updateAccount(AccountBaseForm)
     */
    @Override
    public R<?> updateAccount(AccountBaseForm form) {
        AccountEntity entity = accountService.findByUsername(SecurityUtils.getUsername());
        if (ObjectUtils.isEmpty(entity)) {
            return R.error();
        }
        entity.setDisplayName(form.getDisplayName());
        entity.setSex(form.getSex());
        entity.setBirthday(form.getBirthday());
        accountService.updateById(entity);
        return R.success();
    }

    /**
     * @see AccountApi#forgotPassword(AccountForgotPasswordForm)
     */
    @Override
    public R<AccountForgotPasswordDto> forgotPassword(AccountForgotPasswordForm form) {
        // 检测验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
            .type(CaptchaTypeEnum.EMAIL)
            .email(form.getEmail())
            .key(form.getCaptchaKey())
            .value(form.getCaptchaValue())
            .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 检测邮箱和用户名是否存在
        AccountEntity entity = accountService.findByEmail(form.getEmail());
        if (entity != null) {
            return R.success(AccountForgotPasswordDto.builder().email(entity.getEmail()).username(entity.getUsername()).build());
        }
        return R.error();
    }

    /**
     * @see AccountApi#resetPassword(AccountResetPasswordForm)
     */
    @Override
    public R<?> resetPassword(AccountResetPasswordForm form) {
        // 校验并删除验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
            .type(CaptchaTypeEnum.EMAIL)
            .email(form.getEmail())
            .key(form.getCaptchaKey())
            .value(form.getCaptchaValue())
            .build();
        if (!captchaService.validate(captchaCheckRequest)) {
            return R.error();
        }
        // 检测邮箱和用户名是否存在
        AccountEntity entity = accountService.findByEmail(form.getEmail());
        if (entity != null) {
            entity.setPassword(passwordEncoder.encode(form.getPassword()));
            accountService.updateById(entity);
            return R.success();
        }
        return R.error();
    }

    /**
     * @see AccountApi#changePassword(AccountChangePasswordForm)
     */
    @Override
    public R<?> changePassword(AccountChangePasswordForm form) {
        AccountEntity entity = accountService.findByUsername(SecurityUtils.getUsername());
        if (ObjectUtils.isEmpty(entity) || !passwordEncoder.matches(form.getOriginalPassword(), entity.getPassword())) {
            return R.error();
        }
        entity.setPassword(passwordEncoder.encode(form.getNewPassword()));
        accountService.updateById(entity);
        return R.success();
    }

    /**
     * @see AccountApi#changeEmail(AccountChangeEmailForm)
     */
    @Override
    public R<?> changeEmail(AccountChangeEmailForm form) {
        // 检验就邮箱是否是当前登陆用户的邮箱
        AccountEntity entity = accountService.findByUsername(SecurityUtils.getUsername());
        if (!entity.getEmail().equals(form.getEmail())) {
            return R.error();
        }
        // 检验登陆密码是否正确
        if (!passwordEncoder.matches(form.getPassword(), entity.getPassword())) {
            return R.error();
        }
        // 校验验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
            .type(CaptchaTypeEnum.EMAIL)
            .email(form.getEmail())
            .key(form.getCaptchaKey())
            .value(form.getCaptchaValue())
            .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 修改邮箱
        entity.setEmail(form.getNewEmail());
        accountService.updateById(entity);
        return R.success();
    }

    /**
     * @see AccountApi#logout()
     */
    @Override
    public R<?> logout() {
        if (SecurityUtils.isAnonymous()) {
            return R.success();
        }
        try {
            Long uid = SecurityUtils.getUid();
            String sid = SecurityUtils.getSid();
            String userName = SecurityUtils.getUsername();
            log.info("Account logout username - [{}}] - uid - [{}]. - sid - [{}].", userName, uid, sid);

            if (StringUtils.isNotEmpty(sid)) {
                LoginSessionDto userSession = LoginSessionDto.builder()
                    .actionType(ActionTypeEnum.DELETE)
                    .sessionId(sid)
                    .entityType(EntityTypeEnum.ACCOUNT.getValue())
                    .entityId(uid)
                    .username(userName)
                    .build();
                this.loginSessionRabbitService.send(userSession);
            }
        } catch (Exception e) {
            log.error("Failed to save UserSession.", e);
        }
        return R.success();
    }

    //
    // 辅助方法
    //

    private AccountInfoDto getInfoDto(AccountEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        return SpringUtils.getBean(AccountConverter.class).entity2InfoDto(entity);
    }

    private AccountLoginDto getLoginInfoDto(AccountEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        return SpringUtils.getBean(AccountConverter.class).entity2LoginInfoDto(entity);
    }

}
