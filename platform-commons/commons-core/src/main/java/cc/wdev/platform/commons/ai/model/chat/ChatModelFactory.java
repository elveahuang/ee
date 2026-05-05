package cc.wdev.platform.commons.ai.model.chat;

import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.ModelFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;

/**
 * @author elvea
 */
public interface ChatModelFactory extends ModelFactory<ChatModel> {

    /**
     * 获取智能对话客户端
     */
    default ChatClient getChatClient() {
        return this.getChatClient(this.getModelConfig());
    }

    /**
     * 获取智能对话客户端
     */
    ChatClient getChatClient(ModelConfig config);

    /**
     * 获取智能对话模型
     */
    default ChatModel getChatModel() {
        return this.getModel(this.getModelConfig());
    }

    /**
     * 获取智能对话模型
     */
    default ChatModel getChatModel(ModelConfig config) {
        return this.getModel(config);
    }

}
