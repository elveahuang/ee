package cc.elvea.platform.system.security.service.impl;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.security.cache.AuthorizationCacheKeyGenerator;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity;
import cc.elvea.platform.system.security.repository.AuthorizationMapper;
import cc.elvea.platform.system.security.service.AuthorizationService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see AuthorizationService
 */
@Service
public class AuthorizationServiceImpl extends BaseCachingEntityService<AuthorizationEntity, Long, AuthorizationMapper> implements AuthorizationService {

    private final AuthorizationCacheKeyGenerator cacheKeyGenerator = new AuthorizationCacheKeyGenerator();

    @Override
    public AuthorizationCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    @Override
    public AuthorizationEntity findByState(String state) {
        AuthorizationEntity entity = this.lambdaQueryWrapper().eq(AuthorizationEntity::getState, state).one();
        setCache(entity);
        return entity;
    }

    @Override
    public AuthorizationEntity findByAuthorizationCodeValue(String authorizationCodeValue) {
        AuthorizationEntity entity = this.lambdaQueryWrapper().eq(AuthorizationEntity::getAuthorizationCodeValue, authorizationCodeValue).one();
        setCache(entity);
        return entity;
    }

    @Override
    public AuthorizationEntity findByOidcIdTokenValue(String oidcIdTokenValue) {
        AuthorizationEntity entity = this.lambdaQueryWrapper().eq(AuthorizationEntity::getOidcIdTokenValue, oidcIdTokenValue).one();
        setCache(entity);
        return entity;
    }

    @Override
    public AuthorizationEntity findByAccessTokenValue(String accessTokenValue) {
        AuthorizationEntity entity = this.lambdaQueryWrapper().eq(AuthorizationEntity::getAccessTokenValue, accessTokenValue).one();
        setCache(entity);
        return entity;
    }

    @Override
    public AuthorizationEntity findByRefreshTokenValue(String refreshTokenValue) {
        AuthorizationEntity entity = this.lambdaQueryWrapper().eq(AuthorizationEntity::getRefreshTokenValue, refreshTokenValue).one();
        setCache(entity);
        return entity;
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(AuthorizationEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(getCacheKeyGenerator().keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getState())) {
                getCacheService().set(getCacheKeyGenerator().keyByState(model.getState()), model);
            }
            if (StringUtils.isNotEmpty(model.getAuthorizationCodeValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByAuthorizationCodeValue(model.getAccessTokenValue()), model);
            }
            if (StringUtils.isNotEmpty(model.getOidcIdTokenValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByOidcIdTokenValue(model.getOidcIdTokenValue()), model);
            }
            if (StringUtils.isNotEmpty(model.getAccessTokenValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByAccessTokenValue(model.getAccessTokenValue()), model);
            }
            if (StringUtils.isNotEmpty(model.getRefreshTokenValue())) {
                getCacheService().set(getCacheKeyGenerator().keyByRefreshTokenValue(model.getRefreshTokenValue()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(AuthorizationEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(getCacheKeyGenerator().keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getState())) {
                getCacheService().delete(getCacheKeyGenerator().keyByState(model.getState()));
            }
            if (StringUtils.isNotEmpty(model.getAuthorizationCodeValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByAuthorizationCodeValue(model.getAccessTokenValue()));
            }
            if (StringUtils.isNotEmpty(model.getOidcIdTokenValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByOidcIdTokenValue(model.getOidcIdTokenValue()));
            }
            if (StringUtils.isNotEmpty(model.getAccessTokenValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByAccessTokenValue(model.getAccessTokenValue()));
            }
            if (StringUtils.isNotEmpty(model.getRefreshTokenValue())) {
                getCacheService().delete(getCacheKeyGenerator().keyByRefreshTokenValue(model.getRefreshTokenValue()));
            }
        }
    }

}
