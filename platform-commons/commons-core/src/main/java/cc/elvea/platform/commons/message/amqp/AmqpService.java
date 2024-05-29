package cc.elvea.platform.commons.message.amqp;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface AmqpService<T> {

    /**
     * 发送消息
     *
     * @param body 消息内容
     */
    void send(T body) throws Exception;

    /**
     * 发送消息
     *
     * @param routingKey 队列名称
     * @param body       消息内容
     */
    void send(String routingKey, T body) throws Exception;

    /**
     * 发送消息
     *
     * @param exchange   交换机
     * @param routingKey 队列名称
     * @param body       消息内容
     */
    void send(String exchange, String routingKey, T body) throws Exception;

    default String getExchange() {
        return "";
    }

}
