package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.data.core.domain.IdEntity;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.core.cache.TagTypeCacheKeyGenerator;
import cc.wdev.platform.system.core.domain.converter.TagTypeConverter;
import cc.wdev.platform.system.core.domain.entity.TagTypeEntity;
import cc.wdev.platform.system.core.domain.vo.TagTypeVo;
import cc.wdev.platform.system.core.repository.TagTypeRepository;
import cc.wdev.platform.system.core.service.DictTypeService;
import cc.wdev.platform.system.core.service.TagTypeService;
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
public class TagTypeServiceImpl
    extends BaseCachingEntityService<TagTypeEntity, Long, TagTypeRepository>
    implements TagTypeService {

    private final TagTypeCacheKeyGenerator cacheKeyGenerator = new TagTypeCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see DictTypeService#getDictTypeId(String)
     */
    @Override
    public Long getTagTypeId(String code) {
        TagTypeEntity entity = this.findByCode(code);
        if (entity != null) {
            return entity.getId();
        }
        return 0L;
    }

    /**
     * @see TagTypeService#getTagType(String)
     */
    @Override
    public TagTypeVo getTagType(String code) {
        TagTypeEntity entity = this.findByCode(code);
        return SpringUtils.getBean(TagTypeConverter.class).entity2Vo(entity);
    }

    /**
     * @see TagTypeService#findByCode(String)
     */
    @Override
    public TagTypeEntity findByCode(String code) {
        return this.findByCacheKey(cacheKeyGenerator.keyByCode(code), key -> {
            TagTypeEntity condition = TagTypeEntity.builder().code(code).build();
            Example<TagTypeEntity> example = Example.of(condition);
            TagTypeEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(TagTypeEntity model) {
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
    public void deleteCache(TagTypeEntity model) {
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
