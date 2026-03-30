package cc.wdev.platform.commons.ai.model;

import java.util.Map;

/**
 * @author elvea
 */
public interface ModelConfig {

    /**
     * 获取模型名称
     */
    String getName();

    /**
     * 获取 API Key
     */
    String getApiKey();

    /**
     * 获取 API Base URL
     */
    String getBaseUrl();

    /**
     * 获取提供商编码
     */
    String getProviderCode();

    /**
     * 获取模型类型
     */
    String getModelType();

    /**
     * 获取扩展参数
     */
    Map<String, Object> getVariables();

    /**
     * 是否启用深度思考模式
     */
    default Boolean getReturnThinking() {
        return false;
    }

    /**
     * 是否启用联网搜索
     */
    default Boolean getEnableWebSearch() {
        return false;
    }

}
