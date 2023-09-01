package cn.elvea.platform.commons.core.log.aspect;

import cn.elvea.platform.commons.core.log.LogManager;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractLogAspect {

    protected final LogManager logManager;

    public AbstractLogAspect(LogManager logManager) {
        this.logManager = logManager;
    }

}
