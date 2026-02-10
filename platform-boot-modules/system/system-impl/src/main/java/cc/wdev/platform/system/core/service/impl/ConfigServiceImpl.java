package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.core.cache.SimpleCacheKeyGenerator;
import cc.wdev.platform.commons.data.core.domain.IdEntity;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.commons.constants.SystemCacheConstants;
import cc.wdev.platform.system.core.domain.entity.ConfigEntity;
import cc.wdev.platform.system.core.domain.entity.ConfigEntity_;
import cc.wdev.platform.system.core.repository.ConfigRepository;
import cc.wdev.platform.system.core.service.ConfigService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see ConfigService
 * @see BaseCachingEntityService
 */
@Service
public class ConfigServiceImpl extends BaseCachingEntityService<ConfigEntity, Long, ConfigRepository> implements ConfigService {

    private final CacheKeyGenerator cacheKeyGenerator = new SimpleCacheKeyGenerator(SystemCacheConstants.CONFIG);

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see ConfigService#getConfigByKey(String)
     */
    @Override
    public ConfigEntity getConfigByKey(String key) {
        return getCacheService().get(cacheKeyGenerator.keyByCode(key), k -> {
            Specification<ConfigEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(ConfigEntity_.CONFIG_KEY), key));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(ConfigEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getConfigKey())) {
                getCacheService().set(this.cacheKeyGenerator.keyByCode(model.getConfigKey()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(ConfigEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getConfigKey())) {
                getCacheService().delete(this.cacheKeyGenerator.keyByCode(model.getConfigKey()));
            }
        }
    }

}
