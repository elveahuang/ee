package cc.wdev.dev.webapp.ai.chainflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChainWorkflow {

    private final ChatClient chatClient;

    private final String[] systemPrompts;
    ;

    public ChainWorkflow(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
        this.systemPrompts = initializeChainPrompts();
    }

    public String generateFlow(String userPrompt) {
        String response = userPrompt;

        for (int i = 0; i < systemPrompts.length; i++) {
            log.info("Processing Step {}: {}", i, systemPrompts[i]);
            String input = String.format("%s\n\nInput: %s", systemPrompts[i], response);
            ChatResponse chatResponse = this.chatClient.prompt(input).call().chatResponse();
            response = chatResponse.getResult().getOutput().getText();
            chatResponse.getResult();
            if (!chatResponse.getResult().getOutput().getToolCalls().isEmpty()) {
                // 处理工具调用

            }
        }
        return response;
    }

    private String[] initializeChainPrompts() {
        return new String[]{
            """
            你是一個只能智能招聘助手，根據用戶的輸入内容生成關鍵字，並將其用逗號分隔。同時如果需要調用工具，請按照以下格式：
            Tool: [工具名稱]
            Arguments: [工具參數，用JSON格式表示]
            """,
            """

            """
        };
    }
}
