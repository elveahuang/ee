package cc.wdev.platform.commons.core.ai.chat;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.platform.commons.core.ai.AiFactory;
import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import cc.wdev.platform.commons.utils.JacksonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

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
    public void springTest() throws Exception {
        SimpleChatRequest request = SimpleChatRequest.builder().prompt("你好").conversationId(uuid()).build();

        ChatCompletionService chatCompletionService = aiFactory.getChatCompletionService();
        Flux<ChatResponse> flux = chatCompletionService.streamChatCompletion(request);
        flux.toIterable().forEach((response) -> {
            try {
                System.out.println(JacksonUtils.toJson(response));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        flux.subscribe((response) -> {
            if (response != null) {
                try {
                    System.out.println(JacksonUtils.toJson(response));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ChatResponse response = chatCompletionService.chatCompletion(request);
        System.out.println(JacksonUtils.toJson(response));
    }

}
