package cc.wdev.platform.system.ai.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.system.ai.domain.entity.AiChatMemoryEntity;
import cc.wdev.platform.system.ai.domain.entity.AiChatMemoryEntity_;
import cc.wdev.platform.system.ai.repository.AiChatMemoryRepository;
import cc.wdev.platform.system.ai.service.AiChatMemoryService;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
public class AiChatMemoryServiceImpl
    extends BaseCachingEntityService<AiChatMemoryEntity, Long, AiChatMemoryRepository>
    implements AiChatMemoryService {

    /**
     * @see AiChatMemoryService#findByConversationId(String)
     */
    @Override
    public List<AiChatMemoryEntity> findByConversationId(String conversationId) {
        Specification<AiChatMemoryEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (StringUtils.isNotEmpty(conversationId)) {
                predicates.add(builder.equal(root.get(AiChatMemoryEntity_.CONVERSATION_ID), conversationId));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification);
    }

    /**
     * @see AiChatMemoryService#deleteByConversationId(String)
     */
    @Override
    public void deleteByConversationId(String conversationId) {
        this.repository.deleteAll(this.findByConversationId(conversationId));
    }

    /**
     * @see AiChatMemoryService#findConversationIds()
     */
    @Override
    public List<String> findConversationIds() {
        Specification<AiChatMemoryEntity> specification = (root, query, builder) -> {
            if (query != null) {
                query.select(root.get(AiChatMemoryEntity_.CONVERSATION_ID)).distinct(true);
                query.groupBy(root.get(AiChatMemoryEntity_.CONVERSATION_ID));
            }
            List<Predicate> predicates = Lists.newArrayList();
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification).stream()
            .map(AiChatMemoryEntity::getConversationId).toList();
    }

}
