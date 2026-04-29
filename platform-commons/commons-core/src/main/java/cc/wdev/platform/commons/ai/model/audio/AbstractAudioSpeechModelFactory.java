package cc.wdev.platform.commons.ai.model.audio;

import cc.wdev.platform.commons.ai.config.ProviderConfig;
import cc.wdev.platform.commons.ai.enums.ModelType;
import cc.wdev.platform.commons.ai.model.ModelFactory;

/**
 * @author elvea
 */
public abstract class AbstractAudioSpeechModelFactory implements AudioSpeechModelFactory {

    protected final ProviderConfig config;

    public AbstractAudioSpeechModelFactory(ProviderConfig config) {
        this.config = config;
    }

    /**
     * @see ModelFactory#getModelType()
     */
    @Override
    public ModelType getModelType() {
        return ModelType.AUDIO;
    }

}
