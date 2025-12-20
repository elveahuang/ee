package cc.wdev.platform.commons.message.model;

/**
 * @author elvea
 */
public class SimpleTextMessage extends SimpleMessage<String> {
    public SimpleTextMessage() {
        this.setType("text");
    }
}
