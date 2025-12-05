package cc.wdev.dev.webapp.ai;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.dev.webapp.ai.constant.AiConstant;
import cc.wdev.platform.commons.core.ai.AiFactory;
import cc.wdev.platform.commons.core.ai.chat.ChatCompletionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
@Slf4j
public class AiTests extends BaseTests {

    @Autowired
    private AiFactory aiFactory;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(aiFactory);
        ChatCompletionService service = this.aiFactory.getChatCompletionService();
        Assertions.assertNotNull(service);
        ChatClient client = service.getChatClient();
        Assertions.assertNotNull(client);

        PromptTemplate promptTemplate = new PromptTemplate(AiConstant.DEFAULT_SYSTEM_PROMPT);
        promptTemplate.add("username", "elvea");
        promptTemplate.add("currentUsername", "elvea");
        String prompt = promptTemplate.render();

        ChatResponse response = client.prompt()
            .system(prompt)
            .user("我是dada，有没有适合我的课程")
            .call()
            .chatResponse();
        Assertions.assertNotNull(response);
    }

    @Test
    public void streamTest() {
        Assertions.assertNotNull(aiFactory);
        ChatCompletionService service = this.aiFactory.getChatCompletionService();
        Assertions.assertNotNull(service);
        ChatClient client = service.getChatClient();
        Assertions.assertNotNull(client);

        PromptTemplate promptTemplate = new PromptTemplate(AiConstant.DEFAULT_SYSTEM_PROMPT);
        promptTemplate.add("username", "elvea");
        promptTemplate.add("currentUsername", "elvea");
        String prompt = promptTemplate.render();

        ChatResponse response = client.prompt()
            .system(prompt)
            .user("我是dada，有没有适合我的课程")
            .call()
            .chatResponse();
        Assertions.assertNotNull(response);
    }

}
