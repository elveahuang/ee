package top.baihu.platform.system.ai.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.ai.domain.entity.AiChatMemoryEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface AiChatMemoryService extends CachingEntityService<AiChatMemoryEntity, Long> {

    List<AiChatMemoryEntity> findByConversationId(String conversationId);

    void deleteByConversationId(String conversationId);

    List<String> findConversationIds();

}
