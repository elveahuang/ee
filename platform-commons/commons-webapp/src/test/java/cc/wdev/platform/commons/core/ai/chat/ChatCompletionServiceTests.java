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
public class ChatCompletionServiceTests extends BaseTests {

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

        ChatCompletionService chatCompletionService = aiFactory.getChatCompletionService();
        String content = chatCompletionService.chatCompletionText(request);
        Assertions.assertNotNull(content);

        ChatResponse response = chatCompletionService.chatCompletion(request);
        Assertions.assertNotNull(response);
    }

}
