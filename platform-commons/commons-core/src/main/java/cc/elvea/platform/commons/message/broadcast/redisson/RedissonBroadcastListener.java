package cc.elvea.platform.commons.message.broadcast.redisson;

import cc.elvea.platform.commons.message.broadcast.BroadcastMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
public class RedissonBroadcastListener implements ApplicationListener<ApplicationReadyEvent> {

    private RedissonClient client;

    private RedissonBroadcastConfig config;

    private ApplicationEventPublisher publisher;

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        log.info("RedissonBroadcastListener [{}] start.", config.getTopic());
        RTopic topic = this.client.getTopic(config.getTopic());
        // 先移除所有监听器
        topic.removeAllListeners();
        // 再添加监听器
        topic.addListener(BroadcastMessage.class, (channel, message) -> {
            publisher.publishEvent(message.toEvent());
        });
    }

}
