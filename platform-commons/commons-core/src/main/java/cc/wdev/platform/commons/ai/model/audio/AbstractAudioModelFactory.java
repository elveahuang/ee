package cc.wdev.platform.commons.ai.model.audio;

import cc.wdev.platform.commons.ai.enums.ModelType;
import cc.wdev.platform.commons.ai.model.ModelFactory;

/**
 * @author elvea
 */
public abstract class AbstractAudioModelFactory implements AudioModelFactory {

    /**
     * @see ModelFactory#getModelType()
     */
    @Override
    public ModelType getModelType() {
        return ModelType.AUDIO;
    }


}
