package cc.elvea.platform.system.ai.support;

import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.ai.domain.entity.AiChatMemoryEntity;
import cc.elvea.platform.system.ai.service.AiChatMemoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.messages.*;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class JpaChatMemoryRepository implements ChatMemoryRepository {

    private final AiChatMemoryService aiChatMemoryService;

    /**
     * @see ChatMemoryRepository#findConversationIds()
     */
    @Override
    public @NonNull List<String> findConversationIds() {
        return this.aiChatMemoryService.findConversationIds();
    }

    /**
     * @see ChatMemoryRepository#findByConversationId(String)
     */
    @Override
    public @NonNull List<Message> findByConversationId(@NonNull String conversationId) {
        List<AiChatMemoryEntity> entityList = this.aiChatMemoryService.findByConversationId(conversationId);
        if (CollectionUtils.isNotEmpty(entityList)) {
            return entityList.stream().map((entity) -> {
                String content = entity.getContent();
                String type = entity.getType();
                return switch (MessageType.fromValue(type)) {
                    case MessageType.USER -> new UserMessage(content);
                    case MessageType.ASSISTANT -> new AssistantMessage(content);
                    case MessageType.SYSTEM -> new SystemMessage(content);
                    case MessageType.TOOL -> new ToolResponseMessage(List.of());
                };
            }).collect(Collectors.toUnmodifiableList());
        }
        return List.of();
    }

    @Override
    public void saveAll(@NonNull String conversationId, @NonNull List<Message> messages) {
        this.aiChatMemoryService.deleteByConversationId(conversationId);

        if (CollectionUtils.isNotEmpty(messages)) {
            List<AiChatMemoryEntity> entityList = messages.stream().map((message) -> AiChatMemoryEntity.builder()
                .conversationId(conversationId)
                .content(message.getText())
                .type(message.getMessageType().getValue()).build()
            ).toList();
            this.aiChatMemoryService.saveBatch(entityList);
        }
    }

    @Override
    public void deleteByConversationId(@NonNull String conversationId) {
        this.aiChatMemoryService.deleteByConversationId(conversationId);
    }

}
