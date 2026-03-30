package cc.wdev.platform.commons.ai.model;

import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.enums.ModelType;
import org.springframework.ai.model.Model;

/**
 * @author elvea
 */
public interface ModelFactory<T extends Model<?, ?>> {

    /**
     * 获取模型服务商
     *
     * @return 模型服务商
     *
     */
    ModelProvider getModelProvider();

    /**
     * 获取模型提供程序ID
     *
     * @return 模型提供程序ID
     *
     */
    default String getProviderId() {
        return getModelProvider().getValue();
    }

    /**
     * 获取模型类型
     *
     * @return 模型类型
     */
    ModelType getModelType();

    /**
     * 检查是否支持指定的模型配置
     *
     * @param config 模型配置
     * @return 是否支持
     */
    default boolean supports(ModelConfig config) {
        ModelProvider provider = getModelProvider();
        return provider.getValue().equalsIgnoreCase(config.getProviderCode())
            && provider.supportsModel(config.getName());
    }

    /**
     * 获取默认模型配置
     *
     * @return 模型配置
     */
    ModelConfig getModelConfig();

    /**
     * 获取默认模型
     *
     * @return 模型
     */
    default T getModel() {
        return this.getModel(getModelConfig());
    }

    /**
     * 获取指定模型
     *
     * @param config 模型配置
     * @return 模型
     */
    T getModel(ModelConfig config);

}
