package cc.wdev.platform.commons.message.topic;

/**
 * @author elvea
 */
public interface TopicService<T> {

    void publish(T message) throws Exception;
}
