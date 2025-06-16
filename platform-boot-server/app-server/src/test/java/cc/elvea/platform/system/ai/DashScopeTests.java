package cc.elvea.platform.system.ai;

import cc.elvea.platform.BaseTests;
import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 */
public class DashScopeTests extends BaseTests {

    @Autowired
    private DashScopeChatModel model;

    @Test
    public void test() {
        ChatResponse response = model.call(new Prompt(
            "你好，你是谁",
            DashScopeChatOptions.builder().withModel(DashScopeApi.ChatModel.QWEN_TURBO.getModel()).build()
        ));
        Assertions.assertNotNull(response);
    }

}
