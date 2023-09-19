package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.commons.core.utils.StringUtils;
import cn.elvea.platform.system.core.cache.ConfigCacheKeyGenerator;
import cn.elvea.platform.system.core.model.entity.ConfigEntity;
import cn.elvea.platform.system.core.model.entity.ConfigEntity_;
import cn.elvea.platform.system.core.repository.ConfigRepository;
import cn.elvea.platform.system.core.service.ConfigService;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @see ConfigService
 * @see BaseCachingEntityService
 * @since 0.0.1
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
        return getCacheService().get(cacheKeyGenerator.byKey(key), k -> {
            Specification<ConfigEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(ConfigEntity_.configKey), key));
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
                getCacheService().set(this.cacheKeyGenerator.byKey(model.getConfigKey()), model);
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
                getCacheService().delete(this.cacheKeyGenerator.byKey(model.getConfigKey()));
            }
        }
    }

}
