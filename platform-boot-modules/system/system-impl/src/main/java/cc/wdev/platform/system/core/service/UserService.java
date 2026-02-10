package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.core.domain.entity.UserEntity;
import cc.wdev.platform.system.core.domain.form.UserForm;
import cc.wdev.platform.system.core.domain.request.UserCheckRequest;

/**
 * @author elvea
 * @see EntityService
 */
public interface UserService extends CachingEntityService<UserEntity, Long> {

    /**
     * 检查用户名是否可用
     */
    boolean check(UserCheckRequest request);

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
    UserEntity findByMobile(String mobileCountryCode, String mobileNumber);

    /**
     * 获取系统管理员
     */
    UserEntity getSystemAdministrator();

    /**
     * 保存用户
     */
    void saveUser(UserForm form);

}
