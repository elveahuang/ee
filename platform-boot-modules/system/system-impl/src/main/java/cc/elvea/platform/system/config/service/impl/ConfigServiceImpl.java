package cc.elvea.platform.system.config.service.impl;

import cc.elvea.platform.commons.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.data.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.config.cache.ConfigCacheKeyGenerator;
import cc.elvea.platform.system.config.model.entity.ConfigEntity;
import cc.elvea.platform.system.config.model.entity.ConfigEntity_;
import cc.elvea.platform.system.config.repository.ConfigRepository;
import cc.elvea.platform.system.config.service.ConfigService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see ConfigService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Service
public class ConfigServiceImpl extends BaseCachingEntityService<ConfigEntity, Long, ConfigRepository> implements ConfigService {

    private final ConfigCacheKeyGenerator cacheKeyGenerator = new ConfigCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see ConfigService#getConfigByKey(String)
     */
    @Override
    public ConfigEntity getConfigByKey(String key) {
        return getCacheService().get(cacheKeyGenerator.byCacheKey(key), k -> {
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
                getCacheService().set(this.cacheKeyGenerator.byCacheKey(model.getConfigKey()), model);
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
                getCacheService().delete(this.cacheKeyGenerator.byCacheKey(model.getConfigKey()));
            }
        }
    }

}
