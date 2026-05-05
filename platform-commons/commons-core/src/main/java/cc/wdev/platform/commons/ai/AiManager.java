package cc.wdev.platform.commons.ai;

import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.enums.ServiceProvider;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import cc.wdev.platform.commons.ai.model.chat.ChatModelFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;

import java.util.List;

/**
 * @author elvea
 */
public interface AiManager {

    /**
     * 获取智能体存储服务
     */
    ChatMemory getChatMemory();

    /**
     * 获取默认对话模型工厂
     */
    ChatModelFactory getChatModelFactory();

    /**
     * 获取指定对话模型工厂
     */
    ChatModelFactory getChatModelFactory(ModelConfig config);

    /**
     * 获取默认对话模型
     */
    ChatModel getChatModel();

    /**
     * 获取指定对话模型
     */
    ChatModel getChatModel(ModelConfig config);

    /**
     * 获取默认对话客户端
     */
    ChatClient getChatClient();

    /**
     * 获取指定对话客户端
     */
    ChatClient getChatClient(ModelConfig config);

    /**
     * 获取可用服务提供商
     */
    List<ServiceProvider> getAvailableServiceProviders();

    /**
     * 获取可用提供商
     */
    List<ModelProvider> getAvailableProviders();

}
