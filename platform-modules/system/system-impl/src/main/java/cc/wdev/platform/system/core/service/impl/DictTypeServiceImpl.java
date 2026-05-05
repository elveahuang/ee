package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.data.core.domain.IdEntity;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.core.cache.DictTypeCacheKeyGenerator;
import cc.wdev.platform.system.core.domain.converter.DictTypeConverter;
import cc.wdev.platform.system.core.domain.entity.DictTypeEntity;
import cc.wdev.platform.system.core.domain.vo.DictTypeVo;
import cc.wdev.platform.system.core.repository.DictTypeRepository;
import cc.wdev.platform.system.core.service.DictTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictTypeServiceImpl
    extends BaseCachingEntityService<DictTypeEntity, Long, DictTypeRepository>
    implements DictTypeService {

    private final DictTypeCacheKeyGenerator cacheKeyGenerator = new DictTypeCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see DictTypeService#getDictTypeId(String)
     */
    @Override
    public Long getDictTypeId(String code) {
        DictTypeEntity entity = this.findByCode(code);
        if (entity != null) {
            return entity.getId();
        }
        return 0L;
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public DictTypeVo getDictType(String code) {
        DictTypeEntity entity = this.findByCode(code);
        return SpringUtils.getBean(DictTypeConverter.class).entity2Vo(entity);
    }

    /**
     * @see DictTypeService#findByCode(String)
     */
    @Override
    public DictTypeEntity findByCode(String code) {
        return this.findByCacheKey(cacheKeyGenerator.keyByCode(code), key -> {
            DictTypeEntity condition = DictTypeEntity.builder().code(code).build();
            Example<DictTypeEntity> example = Example.of(condition);
            DictTypeEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(DictTypeEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getCode())) {
                getCacheService().set(this.cacheKeyGenerator.keyByCode(model.getCode()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(DictTypeEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getCode())) {
                getCacheService().delete(this.cacheKeyGenerator.keyByCode(model.getCode()));
            }
        }
    }

}
