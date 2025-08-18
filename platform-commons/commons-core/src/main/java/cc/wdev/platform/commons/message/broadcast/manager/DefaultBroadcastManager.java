package cc.wdev.platform.commons.message.broadcast.manager;

import cc.wdev.platform.commons.message.broadcast.BroadcastSender;
import cc.wdev.platform.commons.message.broadcast.BroadcastType;
import cc.wdev.platform.commons.message.broadcast.rabbit.RabbitBroadcastSender;
import cc.wdev.platform.commons.utils.SpringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
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
        return this.getRabbitSender();
    }

    /**
     * @see BroadcastManager#getRabbitSender()
     */
    @Override
    public RabbitBroadcastSender getRabbitSender() {
        return SpringUtils.getBean(RabbitBroadcastSender.class);
    }

}
