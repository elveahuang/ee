package cc.wdev.platform.commons.message.rabbit;

import cc.wdev.platform.commons.core.Context;
import cc.wdev.platform.commons.utils.JacksonUtils;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class RabbitUtils {

    protected Context context;

    protected RabbitTemplate rabbitTemplate;

    protected MessageConverter messageConverter;

    public <T> void send(String routingKey, T body) throws Exception {
        this.rabbitTemplate.convertAndSend(routingKey, body);
    }

    public <T> void send(String exchange, String routingKey, T body) throws Exception {
        this.rabbitTemplate.convertAndSend(exchange, routingKey, body);
    }

    public <T> void subscribeFanout(Long nodeId, String exchange, String dlx, Class<T> type, Consumer<T> success, Runnable error) {
        rabbitTemplate.execute(channel -> {
            // 每个节点独立队列名
            String queueName = exchange + "." + nodeId;

            // 声明 fanout 交换机
            channel.exchangeDeclare(exchange, "fanout", true);

            // 死信队列名称
            String dlqName = queueName + ".dlq";

            // 声明死信队列（持久化）
            channel.queueDeclare(dlqName, true, false, false, null);

            // 声明主队列，绑定 DLX
            Map<String, Object> args = Maps.newHashMap();
            args.put("x-dead-letter-exchange", dlx); // 显式 DLX
            args.put("x-dead-letter-routing-key", dlqName); // 发送到 DLQ
            channel.queueDeclare(queueName, true, false, false, args);
            channel.queueBind(queueName, exchange, "");

            // 声明 DLX 交换机，并绑定 DLQ 队列
            channel.exchangeDeclare(dlx, "direct", true);
            channel.queueBind(dlqName, dlx, dlqName);

            // 消费消息，异常保护 + DLQ
            channel.basicConsume(queueName, false, (tag, delivery) -> {
                try {
                    T msg = convertMessage(delivery.getBody(), type);
                    success.accept(msg);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false); // 成功 ack
                } catch (Exception e) {
                    log.error("Error processing message: {}", e.getMessage(), e);
                    channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false); // 进入 DLQ
                }
            }, _ -> {
            });

            // 监听 DLQ
            channel.basicConsume(dlqName, true, (tag, delivery) -> {
                log.warn("Message sent to DLQ {}: {}", dlqName, new String(delivery.getBody(), StandardCharsets.UTF_8));
                error.run();
            }, _ -> {
            });
            return null;
        });
    }

    public <T> T convertMessage(byte[] body, Class<T> clazz) {
        try {
            MessageProperties props = new MessageProperties();
            Message message = new Message(body, props);
            Object obj = messageConverter.fromMessage(message); // LinkedHashMap
            return JacksonUtils.getObjectMapper().convertValue(obj, clazz);
        } catch (Exception e) {
            log.error("Error convert rabbit message, type is {}", clazz, e);
        }
        return null;
    }
}
