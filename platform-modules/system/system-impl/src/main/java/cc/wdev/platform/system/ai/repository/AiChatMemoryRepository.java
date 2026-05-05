package cc.wdev.platform.system.ai.repository;

import cc.wdev.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.wdev.platform.system.ai.domain.entity.AiChatMemoryEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AiChatMemoryRepository extends BaseEntityRepository<AiChatMemoryEntity, Long> {
}
