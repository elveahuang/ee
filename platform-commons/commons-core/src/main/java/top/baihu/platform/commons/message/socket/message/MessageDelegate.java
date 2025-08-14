package top.baihu.platform.commons.message.socket.message;

/**
 * @author elvea
 */
public interface MessageDelegate<T> {

    void handleMessage(T message) throws Exception;

}
