package cn.elvea.platform.commons.core.message.amqp;

/**
 * @author elvea
 * @since 0.0.1
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
     * @param queueName 队列名称
     * @param body      消息内容
     */
    void send(String queueName, T body) throws Exception;

}
