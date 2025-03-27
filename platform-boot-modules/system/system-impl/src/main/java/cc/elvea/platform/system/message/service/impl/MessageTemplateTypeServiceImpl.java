package cc.elvea.platform.system.message.service.impl;

import cc.elvea.platform.commons.data.core.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.message.cache.MessageTemplateTypeCacheKeyGenerator;
import cc.elvea.platform.system.message.model.entity.MessageTemplateTypeEntity;
import cc.elvea.platform.system.message.model.entity.MessageTemplateTypeEntity_;
import cc.elvea.platform.system.message.repository.MessageTemplateTypeRepository;
import cc.elvea.platform.system.message.service.MessageTemplateTypeService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
@Service
public class MessageTemplateTypeServiceImpl
        extends BaseCachingEntityService<MessageTemplateTypeEntity, Long, MessageTemplateTypeRepository>
        implements MessageTemplateTypeService {

    private final MessageTemplateTypeCacheKeyGenerator cacheKeyGenerator = new MessageTemplateTypeCacheKeyGenerator();

    @Override
    public MessageTemplateTypeCacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see MessageTemplateTypeService#findByCode(String)
     */
    @Override
    public MessageTemplateTypeEntity findByCode(String code) {
        return getCacheService().get(cacheKeyGenerator.byCode(code), k -> {
            Specification<MessageTemplateTypeEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(MessageTemplateTypeEntity_.CODE), code));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(MessageTemplateTypeEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (!StringUtils.isEmpty(model.getCode())) {
                getCacheService().set(this.cacheKeyGenerator.byCode(model.getCode()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(MessageTemplateTypeEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getCode())) {
                getCacheService().delete(this.cacheKeyGenerator.byCode(model.getCode()));
            }
        }
    }

}
