package cc.wdev.platform.commons.message.model;

import java.io.Serializable;

/**
 * @author elvea
 */
public final class SimpleJsonMessage<C extends Serializable> extends SimpleMessage<C> {
    public SimpleJsonMessage() {
        this.setType("json");
    }

    public SimpleJsonMessage(C data) {
        this.setType("json");
        this.setContent(data);
    }
}
