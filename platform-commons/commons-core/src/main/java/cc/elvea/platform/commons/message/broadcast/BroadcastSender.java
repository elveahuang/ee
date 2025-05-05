package cc.elvea.platform.commons.message.broadcast;

/**
 * @author elvea
 */
public interface BroadcastSender {

    /**
     * 发送广播消息
     */
    <E extends BroadcastMessage<?, ? extends BroadcastEvent<?>>> void sendMessage(E message) throws Exception;

}
