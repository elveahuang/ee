package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.system.core.cache.UserCacheKeyGenerator;
import cn.elvea.platform.system.core.model.entity.UserEntity;
import cn.elvea.platform.system.core.repository.UserRepository;
import cn.elvea.platform.system.core.service.UserService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UserService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Service
public class UserServiceImpl extends BaseCachingEntityService<UserEntity, Long, UserRepository> implements UserService {

    private final UserCacheKeyGenerator cacheKeyGenerator = new UserCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see UserService#findByUsername(String)
     */
    @Override
    public UserEntity findByUsername(String username) {
        return this.findByCacheKey(cacheKeyGenerator.keyByUsername(username), key -> {
            UserEntity condition = UserEntity.builder().username(username).build();
            Example<UserEntity> example = Example.of(condition);
            UserEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see UserService#findByEmail(String)
     */
    @Override
    public UserEntity findByEmail(String email) {
        return this.findByCacheKey(cacheKeyGenerator.keyByEmail(email), key -> {
            UserEntity condition = UserEntity.builder().email(email).build();
            Example<UserEntity> example = Example.of(condition);
            UserEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see UserService#findByMobile(String, String)
     */
    @Override
    public UserEntity findByMobile(String mobileCountryCode, String mobile) {
        return this.findByCacheKey(cacheKeyGenerator.keyByMobile(mobileCountryCode, mobile), key -> {
            UserEntity condition = UserEntity.builder().mobileCountryCode(mobileCountryCode).mobileNumber(mobile).build();
            Example<UserEntity> example = Example.of(condition);
            UserEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(UserEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().set(this.cacheKeyGenerator.keyByUsername(model.getUsername()), model);
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().set(this.cacheKeyGenerator.keyByEmail(model.getEmail()), model);
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobileNumber())) {
                getCacheService().set(this.cacheKeyGenerator.keyByMobile(model.getMobileCountryCode(), model.getMobileNumber()), model);
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
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getUsername())) {
                getCacheService().delete(this.cacheKeyGenerator.keyByUsername(model.getUsername()));
            }
            if (StringUtils.isNotEmpty(model.getEmail())) {
                getCacheService().delete(this.cacheKeyGenerator.keyByEmail(model.getEmail()));
            }
            if (StringUtils.isNotEmpty(model.getMobileCountryCode()) && StringUtils.isNotEmpty(model.getMobileNumber())) {
                getCacheService().delete(this.cacheKeyGenerator.keyByMobile(model.getMobileCountryCode(), model.getMobileNumber()));
            }
        }
    }

}
