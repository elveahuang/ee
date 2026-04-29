package cc.wdev.platform.commons.ai.model.embedding;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ModelType;
import org.springframework.ai.embedding.EmbeddingModel;

/**
 * @author elvea
 */
public abstract class AbstractEmbeddingModelFactory implements EmbeddingModelFactory {

    protected final ProviderConfig config;

    public AbstractEmbeddingModelFactory(ProviderConfig config) {
        this.config = config;
    }

    /**
     * @see EmbeddingModelFactory#getModelType()
     */
    @Override
    public ModelType getModelType() {
        return ModelType.EMBEDDING;
    }

    /**
     * @see EmbeddingModelFactory#getModel()
     */
    @Override
    public EmbeddingModel getModel() {
        return null;
    }

}
