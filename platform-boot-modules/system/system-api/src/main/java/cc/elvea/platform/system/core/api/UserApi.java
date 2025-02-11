package cc.elvea.platform.system.core.api;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.system.core.model.dto.UserForgotPasswordDto;
import cc.elvea.platform.system.core.model.dto.UserInfoDto;
import cc.elvea.platform.system.core.model.dto.UserLoginInfoDto;
import cc.elvea.platform.system.core.model.form.*;
import cc.elvea.platform.system.core.model.request.UserCheckRequest;

/**
 * @author elvea
 */
public interface UserApi {

    /**
     * 检查用户名，邮箱或者手机号码是否可用
     */
    boolean check(UserCheckRequest request);

    /**
     * 用户注册
     */
    R<?> register(UserRegisterForm form);

    /**
     * 获取登录用户信息
     */
    UserInfoDto getUserInfo(String username);

    /**
     * 根据用户名查找用户
     */
    UserLoginInfoDto findByUsername(String username);

    /**
     * 根据手机号码查找用户
     */
    UserLoginInfoDto findByMobile(String mobileCountryCode, String mobileNumber);

    /**
     * 根据邮箱查找用户
     */
    UserLoginInfoDto findByEmail(String email);

    /**
     * 修改用户个人信息
     */
    R<?> updateAccount(UserBaseForm userAccountForm);

    /**
     * 忘记密码
     */
    R<UserForgotPasswordDto> forgotPassword(UserForgotPasswordForm form);

    /**
     * 重置个人密码
     */
    R<?> resetPassword(UserResetPasswordForm form);

    /**
     * 修改个人密码
     */
    R<?> changePassword(UserChangePasswordForm form);

    /**
     * 修改个人邮箱
     */
    R<?> changeEmail(UserChangeEmailForm form);

    /**
     * 退出登录
     */
    R<?> logout();

}
