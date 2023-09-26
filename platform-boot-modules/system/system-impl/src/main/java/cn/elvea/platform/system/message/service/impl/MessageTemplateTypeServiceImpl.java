package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.message.cache.MessageTemplateTypeCacheKeyGenerator;
import cn.elvea.platform.system.message.model.entity.MessageTemplateTypeEntity;
import cn.elvea.platform.system.message.model.entity.MessageTemplateTypeEntity_;
import cn.elvea.platform.system.message.repository.MessageTemplateTypeRepository;
import cn.elvea.platform.system.message.service.MessageTemplateTypeService;
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
                predicates.add(builder.equal(root.get(MessageTemplateTypeEntity_.code), code));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

}
