package cn.elvea.platform.system.message.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseEntityService;
import cn.elvea.platform.system.message.model.entity.MessageContentEntity;
import cn.elvea.platform.system.message.model.entity.MessageContentEntity_;
import cn.elvea.platform.system.message.repository.MessageContentRepository;
import cn.elvea.platform.system.message.service.MessageContentService;
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
public class MessageContentServiceImpl
        extends BaseEntityService<MessageContentEntity, Long, MessageContentRepository>
        implements MessageContentService {

    /**
     * @see MessageContentService#findByMessage(Long)
     */
    @Override
    public List<MessageContentEntity> findByMessage(Long messageId) {
        Specification<MessageContentEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.get(MessageContentEntity_.messageId), messageId));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

}
