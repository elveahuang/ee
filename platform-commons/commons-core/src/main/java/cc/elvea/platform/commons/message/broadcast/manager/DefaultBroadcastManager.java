package cc.elvea.platform.commons.message.broadcast.manager;

import cc.elvea.platform.commons.message.broadcast.BroadcastSender;
import cc.elvea.platform.commons.message.broadcast.BroadcastType;
import cc.elvea.platform.commons.message.broadcast.rabbit.RabbitBroadcastSender;
import cc.elvea.platform.commons.message.broadcast.redisson.RedissonBroadcastSender;
import cc.elvea.platform.commons.utils.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
public class DefaultBroadcastManager implements BroadcastManager {

    private final BroadcastType type;

    /**
     * @see BroadcastManager#getSender()
     */
    @Override
    public BroadcastSender getSender() {
        if (BroadcastType.Redisson.equals(this.type)) {
            return this.getRedissonSender();
        }
        return this.getRabbitSender();
    }

    /**
     * @see BroadcastManager#getRedissonSender()
     */
    @Override
    public RedissonBroadcastSender getRedissonSender() {
        return SpringUtils.getBean(RedissonBroadcastSender.class);
    }

    /**
     * @see BroadcastManager#getRabbitSender()
     */
    @Override
    public RabbitBroadcastSender getRabbitSender() {
        return SpringUtils.getBean(RabbitBroadcastSender.class);
    }

}
