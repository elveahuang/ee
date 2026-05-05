package cc.wdev.platform.commons.ai.model;

import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.enums.ModelType;
import cc.wdev.platform.commons.ai.enums.ServiceProvider;
import cc.wdev.platform.commons.enums.BaseEnum;
import org.springframework.ai.model.Model;

/**
 * @author elvea
 */
public interface ModelFactory<T extends Model<?, ?>> {

    /**
     * 获取服务提供商
     *
     * @return 服务提供商
     *
     */
    ServiceProvider getServiceProvider();

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
        ServiceProvider serviceProvider = getServiceProvider();
        ModelProvider modelProvider = BaseEnum.getEnumByValue(config.getModelProvider(), ModelProvider.class);
        return serviceProvider.getValue().equalsIgnoreCase(config.getServiceProvider())
            && modelProvider != null
            && modelProvider.supportsModel(config.getName());
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
