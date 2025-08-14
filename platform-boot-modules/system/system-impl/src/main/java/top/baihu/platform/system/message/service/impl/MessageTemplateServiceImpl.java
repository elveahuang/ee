package top.baihu.platform.system.message.service.impl;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.core.domain.IdEntity;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.utils.ObjectUtils;
import top.baihu.platform.system.message.cache.MessageTemplateCacheKeyGenerator;
import top.baihu.platform.system.message.domain.entity.MessageTemplateEntity;
import top.baihu.platform.system.message.domain.entity.MessageTemplateEntity_;
import top.baihu.platform.system.message.repository.MessageTemplateRepository;
import top.baihu.platform.system.message.service.MessageTemplateService;

import java.util.List;

/**
 * @author elvea
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
                predicates.add(builder.equal(root.get(MessageTemplateEntity_.TYPE_ID), typeId));
                predicates.add(builder.equal(root.get(MessageTemplateEntity_.TEMPLATE_TYPE_ID), templateTypeId));
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
