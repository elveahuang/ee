package cc.elvea.platform.commons.message.socket.message;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MessageDelegate<T> {

    void handleMessage(T message) throws Exception;

}
