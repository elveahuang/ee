package top.baihu.platform.commons.message.broadcast.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.message.broadcast.BroadcastSender;
import top.baihu.platform.commons.message.broadcast.BroadcastType;
import top.baihu.platform.commons.message.broadcast.rabbit.RabbitBroadcastSender;
import top.baihu.platform.commons.utils.SpringUtils;

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
