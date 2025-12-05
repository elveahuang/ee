package cc.wdev.dev.webapp.ai.utils;


import cc.wdev.platform.commons.core.ai.domain.request.SimpleChatRequest;
import org.springframework.ai.chat.prompt.PromptTemplate;

import static cc.wdev.dev.webapp.ai.constant.AiConstant.DEFAULT_SYSTEM_PROMPT;

/**
 * @author elvea
 */
public class AiUtils {

    /**
     * 预处理请求参数，初始化系统提示此
     */
    public static void handleRequest(SimpleChatRequest request) {
        PromptTemplate promptTemplate = new PromptTemplate(DEFAULT_SYSTEM_PROMPT);
        promptTemplate.add("keyword", "json");
        promptTemplate.add("format", "json");
        promptTemplate.add("username", "elvea");
        promptTemplate.add("currentUsername", "elvea");
        request.setSystemPrompt(promptTemplate.render());
    }

}
