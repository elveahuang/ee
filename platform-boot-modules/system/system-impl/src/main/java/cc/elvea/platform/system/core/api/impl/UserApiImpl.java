package cc.elvea.platform.system.core.api.impl;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.enums.ActionTypeEnum;
import cc.elvea.platform.commons.enums.CaptchaTypeEnum;
import cc.elvea.platform.commons.enums.ResponseCodeEnum;
import cc.elvea.platform.commons.extensions.captcha.request.CaptchaCheckRequest;
import cc.elvea.platform.commons.extensions.captcha.service.CaptchaService;
import cc.elvea.platform.commons.utils.*;
import cc.elvea.platform.system.core.api.UserApi;
import cc.elvea.platform.system.core.model.converter.AuthorityConverter;
import cc.elvea.platform.system.core.model.converter.RoleConverter;
import cc.elvea.platform.system.core.model.converter.UserConverter;
import cc.elvea.platform.system.core.model.dto.UserForgotPasswordDto;
import cc.elvea.platform.system.core.model.dto.UserInfoDto;
import cc.elvea.platform.system.core.model.dto.UserLoginInfoDto;
import cc.elvea.platform.system.core.model.dto.UserSessionDto;
import cc.elvea.platform.system.core.model.entity.AuthorityEntity;
import cc.elvea.platform.system.core.model.entity.RoleEntity;
import cc.elvea.platform.system.core.model.entity.UserEntity;
import cc.elvea.platform.system.core.model.form.*;
import cc.elvea.platform.system.core.model.request.UserCheckRequest;
import cc.elvea.platform.system.core.service.AuthorityService;
import cc.elvea.platform.system.core.service.RoleService;
import cc.elvea.platform.system.core.service.UserService;
import cc.elvea.platform.system.core.service.UserSessionAmqpService;
import com.lark.oapi.core.utils.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserService userService;

    private final AuthorityService authorityService;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final CaptchaService captchaService;

    private final UserSessionAmqpService userSessionAmqpService;

    /**
     * @see UserApi#check(UserCheckRequest)
     */
    @Override
    public boolean check(UserCheckRequest request) {
        return this.userService.check(request);
    }

    /**
     * @see UserApi#register(UserRegisterForm)
     */
    @Override
    public R<?> register(UserRegisterForm form) {
        // 检测验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(form.getEmail())
                .key(form.getCaptchaKey())
                .value(form.getCaptchaValue())
                .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 检测用户名是否可用
        UserCheckRequest usernameCheckRequest = UserCheckRequest.builder().username(form.getUsername()).build();
        if (!userService.check(usernameCheckRequest)) {
            return R.fail(ResponseCodeEnum.USER__USERNAME_NOT_AVAILABLE);
        }
        // 检测邮箱是否可用
        UserCheckRequest emailCheckRequest = UserCheckRequest.builder().email(form.getEmail()).build();
        if (!userService.check(emailCheckRequest)) {
            return R.fail(ResponseCodeEnum.USER__EMAIL_NOT_AVAILABLE);
        }
        // 保存用户
        UserEntity entity = UserEntity.builder()
                .username(form.getUsername())
                .displayName(form.getUsername())
                .name(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .build();
        userService.save(entity);
        return R.success();
    }

    /**
     * @see UserApi#getUserInfo(String)
     */
    @Override
    public UserInfoDto getUserInfo(String username) {
        UserEntity entity = userService.findByUsername(username);
        return getInfoDto(entity);
    }

    /**
     * @see UserApi#findByUsername(String)
     */
    @Override
    public UserLoginInfoDto findByUsername(String username) {
        UserEntity entity = userService.findByUsername(username);
        return getLoginInfoDto(entity);
    }

    /**
     * @see UserApi#findByMobile(String, String)
     */
    @Override
    public UserLoginInfoDto findByMobile(String mobileCountryCode, String mobileNumber) {
        UserEntity entity = userService.findByMobile(mobileCountryCode, mobileNumber);
        return getLoginInfoDto(entity);
    }

    /**
     * @see UserApi#findByEmail(String)
     */
    @Override
    public UserLoginInfoDto findByEmail(String email) {
        UserEntity entity = userService.findByEmail(email);
        return getLoginInfoDto(entity);
    }

    /**
     * @see UserApi#updateAccount(UserBaseForm)
     */
    @Override
    public R<?> updateAccount(UserBaseForm form) {
        UserEntity entity = userService.findByUsername(SecurityUtils.getUsername());
        if (ObjectUtils.isEmpty(entity)) {
            return R.error();
        }
        entity.setDisplayName(form.getDisplayName());
        entity.setSex(form.getSex());
        entity.setBirthday(form.getBirthday());
        userService.updateById(entity);
        return R.success();
    }

    /**
     * @see UserApi#forgotPassword(UserForgotPasswordForm)
     */
    @Override
    public R<UserForgotPasswordDto> forgotPassword(UserForgotPasswordForm form) {
        // 检测验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(form.getEmail())
                .key(form.getCaptchaKey())
                .value(form.getCaptchaValue())
                .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 检测邮箱和用户名是否存在
        UserEntity userEntity = userService.findByEmail(form.getEmail());
        if (userEntity != null) {
            return R.success(UserForgotPasswordDto.builder().email(userEntity.getEmail()).username(userEntity.getUsername()).build());
        }
        return R.error();
    }

    /**
     * @see UserApi#resetPassword(UserResetPasswordForm)
     */
    @Override
    public R<?> resetPassword(UserResetPasswordForm form) {
        // 校验并删除验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(form.getEmail())
                .key(form.getCaptchaKey())
                .value(form.getCaptchaValue())
                .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 检测邮箱和用户名是否存在
        UserEntity userEntity = userService.findByEmail(form.getEmail());
        if (userEntity != null) {
            userEntity.setPassword(passwordEncoder.encode(form.getPassword()));
            userService.updateById(userEntity);
            return R.success();
        }
        return R.error();
    }

    /**
     * @see UserApi#changePassword(UserChangePasswordForm)
     */
    @Override
    public R<?> changePassword(UserChangePasswordForm changePasswordForm) {
        UserEntity userEntity = userService.findByUsername(SecurityUtils.getUsername());
        if (ObjectUtils.isEmpty(userEntity) || !passwordEncoder.matches(changePasswordForm.getOriginalPassword(), userEntity.getPassword())) {
            return R.error();
        }
        userEntity.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        userService.updateById(userEntity);
        return R.success();
    }

    /**
     * @see UserApi#changeEmail(UserChangeEmailForm)
     */
    @Override
    public R<?> changeEmail(UserChangeEmailForm form) {
        // 检验就邮箱是否是当前登陆用户的邮箱
        UserEntity entity = userService.findByUsername(SecurityUtils.getUsername());
        if (!entity.getEmail().equals(form.getEmail())) {
            return R.error();
        }
        // 检验登陆密码是否正确
        if (!passwordEncoder.matches(form.getPassword(), entity.getPassword())) {
            return R.error();
        }
        // 校验验证码
        CaptchaCheckRequest captchaCheckRequest = CaptchaCheckRequest.builder()
                .type(CaptchaTypeEnum.MAIL)
                .email(form.getEmail())
                .key(form.getCaptchaKey())
                .value(form.getCaptchaValue())
                .build();
        if (!captchaService.check(captchaCheckRequest)) {
            return R.error();
        }
        // 修改邮箱
        entity.setEmail(form.getNewEmail());
        userService.updateById(entity);
        return R.success();
    }

    /**
     * @see UserApi#logout()
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
            log.info("User logout username - [{}}] - uid - [{}]. - sid - [{}].", userName, uid, sid);

            if (StringUtils.isNotEmpty(sid)) {
                UserSessionDto userSession = UserSessionDto.builder()
                        .actionType(ActionTypeEnum.DELETE)
                        .sessionId(sid)
                        .userId(uid)
                        .username(userName)
                        .build();
                this.userSessionAmqpService.send(userSession);
            }
        } catch (Exception e) {
            log.error("Failed to save UserSession.", e);
        }
        return R.success();
    }

    //
    // 辅助方法
    //

    private UserInfoDto getInfoDto(UserEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }

        UserInfoDto user = SpringUtils.getBean(UserConverter.class).entity2InfoDto(entity);

        // 查询用户所有权限和角色信息
        List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
        List<RoleEntity> roleEntityList = roleService.findByUserId(user.getId());
        // 合并权限和角色统一为权限
        List<String> roles = Lists.newArrayList();
        List<String> authorities = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(authorityEntityList)) {
            authorities.addAll(authorityEntityList.stream().map(AuthorityEntity::getCode).toList());
        }
        if (CollectionUtils.isNotEmpty(roleEntityList)) {
            roles.addAll(roleEntityList.stream().map(RoleEntity::getCode).toList());
            authorities.addAll(roles);
        }
        user.setAuthorities(CollectionUtils.isNotEmpty(authorityEntityList) ? authorities : Collections.emptyList());
        user.setRoles(CollectionUtils.isNotEmpty(roleEntityList) ? roles : Collections.emptyList());

        return user;
    }

    private UserLoginInfoDto getLoginInfoDto(UserEntity entity) {
        if (ObjectUtils.isEmpty(entity)) {
            return null;
        }
        UserLoginInfoDto user = SpringUtils.getBean(UserConverter.class).entity2LoginInfoDto(entity);
        List<AuthorityEntity> authorityEntityList = authorityService.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(authorityEntityList)) {
            user.setAuthorities(SpringUtils.getBean(AuthorityConverter.class).entityListToDtoList(authorityEntityList));
        }
        List<RoleEntity> roleEntityList = roleService.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(roleEntityList)) {
            user.setRoles(SpringUtils.getBean(RoleConverter.class).entityListToDtoList(roleEntityList));
        }
        return user;
    }

}
