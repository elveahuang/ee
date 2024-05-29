package cc.elvea.platform.commons.message.broadcast;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface BroadcastSender {

    /**
     * 发送广播消息
     */
    <E extends BroadcastMessage<?, ? extends BroadcastEvent<?>>> void sendMessage(E message) throws Exception;

}
