package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.dev.webapp.ai.utils.AiUtils;
import cc.wdev.platform.commons.core.ai.AiFactory;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;

import static cn.hutool.core.util.StrUtil.uuid;

/**
 * @author elvea
 */
public class ChatServiceTests extends BaseTests {

    @Autowired
    private AiFactory aiFactory;

    @Test
    public void baseTest() throws Exception {
        Assertions.assertNotNull(this.aiFactory);
    }

    @Test
    public void springTest() {
        SimpleChatRequest request = SimpleChatRequest.builder()
            .prompt("你好")
            .conversationId(uuid())
            .tool("getVersion")
            .build();
        AiUtils.handleRequest(request);

        ChatService chatService = aiFactory.getChatService();
        String content = chatService.chatCompletionText(request);
        Assertions.assertNotNull(content);

        ChatResponse response = chatService.chatCompletion(request);
        Assertions.assertNotNull(response);
    }

}
