package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.system.core.cache.UserCacheKeyGenerator;
import cn.elvea.platform.system.core.model.dto.*;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.mapper.UserMapper;
import cn.elvea.platform.system.core.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UserService
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseCachingEntityService<UserEntity, Long, UserMapper> implements UserService {

    private final UserCacheKeyGenerator cacheKeyGenerator = new UserCacheKeyGenerator();

    private final EntityRelationService entityRelationService;

    private final PositionService positionService;

    private final OrganizationService organizationService;

    private final AuthorityService authorityService;

    private final RoleService roleService;

    // ------------------------------------------------------------------------------------------------------------------------
    // 扩展覆盖父类方法
    // ------------------------------------------------------------------------------------------------------------------------

    /**
     * @see BaseCachingEntityService#getCacheKeyGenerator()
     */
    @Override
    public UserCacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(UserEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(getCacheKeyGenerator().keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().set(getCacheKeyGenerator().keyByUsername(model.getUsername()), model);
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().set(getCacheKeyGenerator().keyByEmail(model.getEmail()), model);
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobile())) {
                getCacheService().set(getCacheKeyGenerator().keyByMobile(model.getMobileCountryCode(), model.getMobile()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(UserEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(getCacheKeyGenerator().keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().delete(getCacheKeyGenerator().keyByUsername(model.getUsername()));
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().delete(getCacheKeyGenerator().keyByEmail(model.getEmail()));
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobile())) {
                getCacheService().delete(getCacheKeyGenerator().keyByMobile(model.getMobileCountryCode(), model.getMobile()));
            }
        }
    }

    // ------------------------------------------------------------------------------------------------------------------------
    // 实现服务接口方法
    // ------------------------------------------------------------------------------------------------------------------------

    /**
     * @see UserService#findByUsername(String)
     */
    @Override
    public UserEntity findByUsername(String username) {
        return lambdaQuery().eq(UserEntity::getUsername, username).one();
    }

    /**
     * @see UserService#findByEmail(String)
     */
    @Override
    public UserEntity findByEmail(String email) {
        return lambdaQuery().eq(UserEntity::getEmail, email).one();
    }

    /**
     * @see UserService#findByMobileCountryCodeAndMobile(String, String)
     */
    @Override
    public UserEntity findByMobileCountryCodeAndMobile(String mobileCountryCode, String mobile) {
        return lambdaQuery()
                .eq(UserEntity::getMobileCountryCode, mobileCountryCode)
                .eq(UserEntity::getMobile, mobile)
                .one();
    }

    /**
     * @see UserService#check(UserCheckDto)
     */
    @Override
    public boolean check(UserCheckDto checkDto) {
        return false;
    }

    /**
     * @see UserService#updatePassword(UserUpdatePasswordDto)
     */
    @Override
    public void updatePassword(UserUpdatePasswordDto passwordDto) {
    }

    /**
     * @see UserService#resetPassword(UserUpdatePasswordDto)
     */
    @Override
    public void resetPassword(UserUpdatePasswordDto passwordDto) {
    }

    /**
     * @see UserService#updateAvatar(UserUpdateAvatarDto)
     */
    @Override
    public void updateAvatar(UserUpdateAvatarDto avatarDto) {
    }

    /**
     * @see UserService#saveUser(UserDto)
     */
    public void saveUser(UserDto userDto) {
    }

    /**
     * @see UserService#register(UserRegisterDto)
     */
    @Override
    public void register(UserRegisterDto registerDto) {
    }

    /**
     * @see UserService#forgotPassword(UserForgotPasswordDto)
     */
    @Override
    public void forgotPassword(UserForgotPasswordDto passwordDto) {
    }

    @Override
    public UserEntity getByUsername(String username) {
        return null;
    }

    @Override
    public UserEntity getByEmail(String email) {
        return null;
    }

    @Override
    public UserEntity getByMobile(String mobileCountryCode, String mobile) {
        return null;
    }

    @Override
    public UserDto saveUser(UserSaveDto saveDto) {
        return null;
    }

}
