package cc.elvea.platform.system.ai.repository;

import cc.elvea.platform.commons.data.jpa.repository.BaseEntityRepository;
import cc.elvea.platform.system.ai.model.entity.AiChatMemoryEntity;
import org.springframework.stereotype.Repository;

/**
 * @author elvea
 */
@Repository
public interface AiChatMemoryRepository extends BaseEntityRepository<AiChatMemoryEntity, Long> {
}
