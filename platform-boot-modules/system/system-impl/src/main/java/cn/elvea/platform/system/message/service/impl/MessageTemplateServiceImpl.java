package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.domain.IdEntity;
import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.commons.core.utils.ObjectUtils;
import cn.elvea.platform.system.message.cache.MessageTemplateCacheKeyGenerator;
import cn.elvea.platform.system.message.model.entity.MessageTemplateEntity;
import cn.elvea.platform.system.message.model.entity.MessageTemplateEntity_;
import cn.elvea.platform.system.message.repository.MessageTemplateRepository;
import cn.elvea.platform.system.message.service.MessageTemplateService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class MessageTemplateServiceImpl
        extends BaseCachingEntityService<MessageTemplateEntity, Long, MessageTemplateRepository>
        implements MessageTemplateService {

    private final MessageTemplateCacheKeyGenerator cacheKeyGenerator = new MessageTemplateCacheKeyGenerator();

    @Override
    public MessageTemplateCacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see MessageTemplateService#findByType(Long, Long)
     */
    @Override
    public MessageTemplateEntity findByType(Long typeId, Long templateTypeId) {
        return getCacheService().get(cacheKeyGenerator.byType(typeId, templateTypeId), k -> {
            Specification<MessageTemplateEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(MessageTemplateEntity_.typeId), typeId));
                predicates.add(builder.equal(root.get(MessageTemplateEntity_.templateTypeId), templateTypeId));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(MessageTemplateEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (!ObjectUtils.isEmpty(model.getTypeId()) && !ObjectUtils.isEmpty(model.getTemplateTypeId())) {
                getCacheService().set(this.cacheKeyGenerator.byType(model.getTypeId(), model.getTemplateTypeId()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(MessageTemplateEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (!ObjectUtils.isEmpty(model.getTypeId()) && !ObjectUtils.isEmpty(model.getTemplateTypeId())) {
                getCacheService().delete(this.cacheKeyGenerator.byType(model.getTypeId(), model.getTemplateTypeId()));
            }
        }
    }

}
