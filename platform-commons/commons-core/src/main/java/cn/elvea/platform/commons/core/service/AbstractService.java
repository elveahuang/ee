package cn.elvea.platform.commons.core.service;

import cn.elvea.platform.commons.core.sequence.Sequence;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractService implements Service {

    protected Sequence sequence;

    /**
     * @see Service#getCurLocalDateTime()
     */
    public LocalDateTime getCurLocalDateTime() {
        return LocalDateTime.now();
    }

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
