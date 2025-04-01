package cc.elvea.platform.system.tag.service;

import cc.elvea.platform.commons.core.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.dict.service.DictTypeService;
import cc.elvea.platform.system.tag.cache.TagTypeCacheKeyGenerator;
import cc.elvea.platform.system.tag.model.converter.TagTypeConverter;
import cc.elvea.platform.system.tag.model.entity.TagTypeEntity;
import cc.elvea.platform.system.tag.model.vo.TagTypeVo;
import cc.elvea.platform.system.tag.repository.TagTypeRepository;
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
