package cc.wdev.platform.system.ai.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.ai.domain.entity.AiChatMemoryEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface AiChatMemoryService extends CachingEntityService<AiChatMemoryEntity, Long> {

    List<AiChatMemoryEntity> findByConversationId(String conversationId);

    void deleteByConversationId(String conversationId);

    List<String> findConversationIds();

}
