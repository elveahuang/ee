package top.baihu.platform.system.security.service.impl;

import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.core.cache.CacheKey;
import top.baihu.platform.commons.data.core.domain.IdEntity;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.utils.ObjectUtils;
import top.baihu.platform.system.security.cache.AuthorizationConsentCacheKeyGenerator;
import top.baihu.platform.system.security.domain.entity.AuthorizationConsentEntity;
import top.baihu.platform.system.security.domain.entity.AuthorizationConsentEntity_;
import top.baihu.platform.system.security.repository.AuthorizationConsentRepository;
import top.baihu.platform.system.security.service.AuthorizationConsentService;

import java.util.List;

/**
 * @author elvea
 * @see AuthorizationConsentService
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
                predicates.add(builder.equal(root.get(AuthorizationConsentEntity_.CLIENT_ID), clientId));
                predicates.add(builder.equal(root.get(AuthorizationConsentEntity_.PRINCIPAL_NAME), principalName));
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
