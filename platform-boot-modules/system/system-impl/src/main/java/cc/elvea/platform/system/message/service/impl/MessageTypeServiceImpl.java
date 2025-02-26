package cc.elvea.platform.system.message.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.message.cache.MessageTypeCacheKeyGenerator;
import cc.elvea.platform.system.message.model.entity.MessageTypeEntity;
import cc.elvea.platform.system.message.model.entity.MessageTypeEntity_;
import cc.elvea.platform.system.message.repository.MessageTypeRepository;
import cc.elvea.platform.system.message.service.MessageTypeService;
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
public class MessageTypeServiceImpl
        extends BaseCachingEntityService<MessageTypeEntity, Long, MessageTypeRepository>
        implements MessageTypeService {

    private final MessageTypeCacheKeyGenerator cacheKeyGenerator = new MessageTypeCacheKeyGenerator();

    @Override
    public MessageTypeCacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see MessageTypeService#findByCode(String)
     */
    @Override
    public MessageTypeEntity findByCode(String code) {
        return getCacheService().get(cacheKeyGenerator.byCode(code), k -> {
            Specification<MessageTypeEntity> specification = (root, query, builder) -> {
                List<Predicate> predicates = Lists.newArrayList();
                predicates.add(builder.equal(root.get(MessageTypeEntity_.CODE), code));
                return builder.and(predicates.toArray(new Predicate[0]));
            };
            return this.repository.findOne(specification).orElse(null);
        });
    }

}
