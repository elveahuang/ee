package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.cache.CacheKeyGenerator;
import cc.wdev.platform.commons.data.core.domain.IdEntity;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.ObjectUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.core.cache.AttachmentTypeCacheKeyGenerator;
import cc.wdev.platform.system.core.domain.converter.AttachmentTypeConverter;
import cc.wdev.platform.system.core.domain.entity.AttachmentTypeEntity;
import cc.wdev.platform.system.core.domain.vo.AttachmentTypeVo;
import cc.wdev.platform.system.core.repository.AttachmentTypeRepository;
import cc.wdev.platform.system.core.service.AttachmentTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
public class AttachmentTypeServiceImpl
    extends BaseCachingEntityService<AttachmentTypeEntity, Long, AttachmentTypeRepository>
    implements AttachmentTypeService {

    private final AttachmentTypeCacheKeyGenerator cacheKeyGenerator = new AttachmentTypeCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see AttachmentTypeService#getAttachmentTypeId(String)
     */
    @Override
    public Long getAttachmentTypeId(String code) {
        AttachmentTypeEntity entity = this.findByCode(code);
        if (entity != null) {
            return entity.getId();
        }
        return 0L;
    }

    /**
     * @see AttachmentTypeService#getAttachmentType(String)
     */
    @Override
    public AttachmentTypeVo getAttachmentType(String code) {
        AttachmentTypeEntity entity = this.findByCode(code);
        return SpringUtils.getBean(AttachmentTypeConverter.class).entity2Vo(entity);
    }

    /**
     * @see AttachmentTypeService#findByCode(String)
     */
    @Override
    public AttachmentTypeEntity findByCode(String code) {
        return this.findByCacheKey(cacheKeyGenerator.keyByCode(code), key -> {
            AttachmentTypeEntity condition = AttachmentTypeEntity.builder().code(code).build();
            Example<AttachmentTypeEntity> example = Example.of(condition);
            AttachmentTypeEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(AttachmentTypeEntity model) {
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
    public void deleteCache(AttachmentTypeEntity model) {
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
