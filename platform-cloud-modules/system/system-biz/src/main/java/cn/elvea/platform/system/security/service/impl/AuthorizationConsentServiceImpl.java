package cn.elvea.platform.system.security.service.impl;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.system.security.cache.AuthorizationConsentCacheKeyGenerator;
import cn.elvea.platform.system.security.model.entity.AuthorizationConsentEntity;
import cn.elvea.platform.system.security.repository.AuthorizationConsentMapper;
import cn.elvea.platform.system.security.service.AuthorizationConsentService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see AuthorizationConsentService
 * @since 0.0.1
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
        return getCacheService().get(cacheKeyGenerator.byKey(clientId, principalName), k -> this.lambdaQuery()
                .eq(AuthorizationConsentEntity::getClientId, clientId)
                .eq(AuthorizationConsentEntity::getPrincipalName, principalName)
                .one());
    }

    /**
     * @see AuthorizationConsentService#deleteByKey(String, String)
     */
    @Override
    public void deleteByKey(String clientId, String principalName) {
        this.lambdaUpdate()
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
