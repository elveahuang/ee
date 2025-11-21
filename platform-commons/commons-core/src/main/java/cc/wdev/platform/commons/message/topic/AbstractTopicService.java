package cc.wdev.platform.commons.message.topic;

import cc.wdev.platform.commons.utils.GenericsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;

/**
 * @author elvea
 */
@Slf4j
public abstract class AbstractTopicService<T> implements TopicService<T> {

    private final TopicConfig config;

    private final TopicManager topicManager;

    public AbstractTopicService(TopicConfig config, ObjectProvider<TopicManager> topicManager) {
        this.config = config;
        this.topicManager = topicManager.getIfAvailable();
        this.init();
    }

    private void init() {
        Class<T> superGenericType = GenericsUtils.getSuperGenericType(getClass(), AbstractTopicService.class, 0);

        if (BroadcastType.Redisson.equals(this.topicManager.getBroadcastType())) {
            log.info("Init topic {} with redisson. Message Type [{}]", config.getName(), superGenericType.getSimpleName());
            topicManager.getRedissonUtils().addListener(config.getName(), superGenericType, (channel, msg) -> {
                log.info("Receive message {} from channel {}.", msg, channel);
                this.receive(msg);
            });
        }

        if (BroadcastType.Rabbit.equals(this.topicManager.getBroadcastType())) {
            log.info("Init topic {} with rabbitmq. Message Type [{}]", config.getName(), superGenericType.getSimpleName());
            topicManager.getRabbitUtils().subscribeFanout(
                topicManager.getNodeId(), config.getExchange(), config.getDlx(), superGenericType,
                (msg) -> {
                    log.info("Receive message {} from exchange {}.", msg, config.getExchange());
                    this.receive(msg);
                }, () -> log.warn("RabbitMQ Subscriber failed to subscribe to exchange {}.", config.getExchange()));
        }
    }

    /**
     * 发送消息到指定的主题
     */
    public void publish(T message) throws Exception {
        if (BroadcastType.Redisson.equals(this.topicManager.getBroadcastType())) {
            log.info("Publish redisson message {} to topic {}.", message, config.getName());
            this.topicManager.getRedissonUtils().getTopic(config.getName()).publish(message);
        }
        if (BroadcastType.Rabbit.equals(this.topicManager.getBroadcastType())) {
            log.info("Publish rabbit message {} to topic {}.", message, config.getName());
            this.topicManager.getRabbitUtils().send(this.config.getExchange(), this.config.getRoutingKey(), message);
        }
    }

    /**
     * 接收指定主题的消息
     */
    public void receive(T message) {
        log.info("Receive message {} to topic {}.", message, config.getName());
        this.handle(message);
    }

    protected abstract void handle(T message);

}
