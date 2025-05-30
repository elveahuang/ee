package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.dto.*;
import cc.elvea.platform.system.core.model.entity.UserEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface UserService extends CachingEntityService<UserEntity, Long> {

    /**
     * 根据用户名查找用户
     */
    UserEntity findByUsername(String username);

    /**
     * 根据邮箱查找用户
     */
    UserEntity findByEmail(String email);

    /**
     * 根据手机查找用户
     */
    UserEntity findByMobileCountryCodeAndMobile(String mobileCountryCode, String mobile);

    /**
     * 检查用户名
     */
    boolean check(UserCheckDto checkDto);

    /**
     * 用户修改密码
     */
    void updatePassword(UserUpdatePasswordDto passwordDto);

    /**
     * 重置用户密码
     */
    void resetPassword(UserUpdatePasswordDto passwordDto);

    /**
     * 更新用户头像
     */
    void updateAvatar(UserUpdateAvatarDto avatarDto);

    /**
     * 保存用户信息
     */
    void saveUser(UserDto userDto);

    /**
     * 用户注册
     */
    void register(UserRegisterDto registerDto);

    /**
     * 用户忘记密码
     */
    void forgotPassword(UserForgotPasswordDto passwordDto);


    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return {@link UserEntity}
     */
    UserEntity getByUsername(String username);

    /**
     * 根据邮箱查询
     *
     * @param email 用户名
     * @return {@link UserEntity}
     */
    UserEntity getByEmail(String email);

    /**
     * 根据手机号码查询
     *
     * @param mobileCountryCode 手机国家区号
     * @param mobile            手机号码
     * @return {@link UserEntity}
     */
    UserEntity getByMobile(String mobileCountryCode, String mobile);

    /**
     * 保存用户
     *
     * @param saveDto {@link UserSaveDto}
     * @return {@link UserDto}
     */
    UserDto saveUser(UserSaveDto saveDto);

}
