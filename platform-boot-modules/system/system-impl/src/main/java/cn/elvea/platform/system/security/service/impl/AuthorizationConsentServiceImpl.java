package cn.elvea.platform.system.security.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKey;
import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.system.security.cache.AuthorizationConsentCacheKeyGenerator;
import cn.elvea.platform.system.security.model.entity.AuthorizationConsentEntity;
import cn.elvea.platform.system.security.model.entity.AuthorizationConsentEntity_;
import cn.elvea.platform.system.security.repository.AuthorizationConsentRepository;
import cn.elvea.platform.system.security.service.AuthorizationConsentService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see AuthorizationConsentService
 * @since 0.0.1
 */
@Service
public class AuthorizationConsentServiceImpl
        extends BaseCachingEntityService<AuthorizationConsentEntity, Long, AuthorizationConsentRepository>
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
        CacheKey cacheKey = getCacheKeyGenerator().byKey(clientId, principalName);
        return getCacheService().get(cacheKey, k -> {
            Specification<AuthorizationConsentEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(AuthorizationConsentEntity_.clientId), clientId));
                predicates.add(builder.equal(root.get(AuthorizationConsentEntity_.principalName), principalName));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    /**
     * @see AuthorizationConsentService#deleteByKey(String, String)
     */
    @Override
    public void deleteByKey(String clientId, String principalName) {
        CacheKey cacheKey = getCacheKeyGenerator().byKey(clientId, principalName);
        this.getCacheService().delete(cacheKey);
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
