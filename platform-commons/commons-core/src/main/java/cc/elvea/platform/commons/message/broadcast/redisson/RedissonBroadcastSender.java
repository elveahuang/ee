package cc.elvea.platform.commons.message.broadcast.redisson;

import cc.elvea.platform.commons.message.broadcast.BroadcastEvent;
import cc.elvea.platform.commons.message.broadcast.BroadcastMessage;
import cc.elvea.platform.commons.message.broadcast.BroadcastSender;
import cc.elvea.platform.commons.utils.JacksonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
public class RedissonBroadcastSender implements BroadcastSender {

    private RedissonClient client;

    private RedissonBroadcastConfig config;

    @Override
    public <E extends BroadcastMessage<?, ? extends BroadcastEvent<?>>> void sendMessage(E message) throws Exception {
        log.info("Send redisson broadcast message [{}] : {}", config.getTopic(), JacksonUtils.toJson(message));
        RTopic topic = this.client.getTopic(config.getTopic());
        topic.publish(message);
    }

}
