package cc.wdev.platform.commons.ai.model.embedding;

import cc.wdev.platform.commons.ai.enums.ModelProvider;
import cc.wdev.platform.commons.ai.enums.ModelType;
import cc.wdev.platform.commons.ai.model.ModelConfig;
import org.springframework.ai.embedding.EmbeddingModel;

/**
 * @author elvea
 */
public abstract class AbstractEmbeddingModelFactory implements EmbeddingModelFactory {

    /**
     * @see EmbeddingModelFactory#getModelType()
     */
    @Override
    public ModelType getModelType() {
        return ModelType.EMBEDDING;
    }

    /**
     * @see EmbeddingModelFactory#getModelType()
     */
    @Override
    public boolean supports(ModelConfig config) {
        ModelProvider provider = getModelProvider();
        return provider.getValue().equalsIgnoreCase(config.getProviderCode())
            && provider.supportsModel(config.getName());
    }

    /**
     * @see EmbeddingModelFactory#getModel()
     */
    @Override
    public EmbeddingModel getModel() {
        return null;
    }

}
