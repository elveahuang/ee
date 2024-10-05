package cc.elvea.platform.commons.service;

import cc.elvea.platform.commons.core.sequence.Sequence;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class AbstractService implements Service {

    protected Sequence sequence;

    /**
     * @see Service#generateId()
     */
    @Override
    public Long generateId() {
        return this.sequence.nextId();
    }

    /**
     * @see Service#generateIdAsString()
     */
    @Override
    public String generateIdAsString() {
        return String.valueOf(this.sequence.nextId());
    }

    /**
     * @see Service#generateCode(String)
     */
    @Override
    public String generateCode(String prefix) {
        return String.format("%s-%s", prefix, generateIdAsString());
    }

    @Autowired
    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

}
