package cc.wdev.platform.system.security.service.impl;

import cc.wdev.platform.commons.data.core.domain.IdEntity;
import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.system.security.cache.AuthorizationConsentCacheKeyGenerator;
import cc.wdev.platform.system.security.domain.entity.AuthorizationConsentEntity;
import cc.wdev.platform.system.security.repository.AuthorizationConsentMapper;
import cc.wdev.platform.system.security.service.AuthorizationConsentService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see AuthorizationConsentService
 */
@Service
public class AuthorizationConsentServiceImpl
    extends BaseCachingEntityService<AuthorizationConsentEntity, Long, AuthorizationConsentMapper>
    implements AuthorizationConsentService {

    private final AuthorizationConsentCacheKeyGenerator cacheKeyGenerator = new AuthorizationConsentCacheKeyGenerator();

    @Override
    public AuthorizationConsentCacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see AuthorizationConsentService#findByKey(String, String)
     */
    @Override
    public AuthorizationConsentEntity findByKey(String clientId, String principalName) {
        return getCacheService().get(cacheKeyGenerator.byKey(clientId, principalName), k -> this.lambdaQueryWrapper()
            .eq(AuthorizationConsentEntity::getClientId, clientId)
            .eq(AuthorizationConsentEntity::getPrincipalName, principalName)
            .one());
    }

    /**
     * @see AuthorizationConsentService#deleteByKey(String, String)
     */
    @Override
    public void deleteByKey(String clientId, String principalName) {
        this.lambdaUpdateWrapper()
            .eq(AuthorizationConsentEntity::getClientId, clientId)
            .eq(AuthorizationConsentEntity::getPrincipalName, principalName)
            .update();
        this.getCacheService().delete(this.cacheKeyGenerator.byKey(clientId, principalName));
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(AuthorizationConsentEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(getCacheKeyGenerator().byKey(model.getClientId(), model.getPrincipalName()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(AuthorizationConsentEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(getCacheKeyGenerator().byKey(model.getClientId(), model.getPrincipalName()));
            }
        }
    }

}
