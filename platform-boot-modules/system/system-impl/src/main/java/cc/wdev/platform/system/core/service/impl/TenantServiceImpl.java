package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKey;
import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.data.core.domain.IdEntity;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.core.domain.entity.TenantEntity;
import cc.wdev.platform.system.core.domain.entity.TenantEntity_;
import cc.wdev.platform.system.core.repository.TenantRepository;
import cc.wdev.platform.system.core.service.TenantService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static cc.wdev.platform.system.commons.constants.SystemCacheConstants.TENANT;

/**
 * @author elvea
 * @see TenantService
 * @see BaseCachingEntityService
 */
@Slf4j
@AllArgsConstructor
@Service
public class TenantServiceImpl extends BaseCachingEntityService<TenantEntity, Long, TenantRepository> implements TenantService {

    private final CacheKeyGenerator cacheKeyGenerator = new SimpleCacheKeyGenerator(TENANT);

    /**
     * @see BaseCachingEntityService#getCacheKeyGenerator()
     */
    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return this.cacheKeyGenerator;
    }

    /**
     * @see TenantService#findByCode(String)
     */
    @Override
    public TenantEntity findByCode(String code) {
        CacheKey cacheKey = getCacheKeyGenerator().keyByCode(code);
        return getCacheService().get(cacheKey, k -> {
            Specification<TenantEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(TenantEntity_.CODE), code));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }


    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(TenantEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(getCacheKeyGenerator().keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getCode())) {
                getCacheService().set(getCacheKeyGenerator().keyByCode(model.getCode()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(TenantEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(getCacheKeyGenerator().keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getCode())) {
                getCacheService().delete(getCacheKeyGenerator().keyByCode(model.getCode()));
            }
        }
    }

}
