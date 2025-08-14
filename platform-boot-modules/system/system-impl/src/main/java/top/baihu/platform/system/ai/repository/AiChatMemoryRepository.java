package top.baihu.platform.system.ai.repository;

import org.springframework.stereotype.Repository;
import top.baihu.platform.commons.data.jpa.repository.BaseEntityRepository;
import top.baihu.platform.system.ai.domain.entity.AiChatMemoryEntity;

/**
 * @author elvea
 */
@Repository
public interface AiChatMemoryRepository extends BaseEntityRepository<AiChatMemoryEntity, Long> {
}
