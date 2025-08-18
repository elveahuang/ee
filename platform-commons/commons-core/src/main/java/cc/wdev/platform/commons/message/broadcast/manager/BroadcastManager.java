package cc.wdev.platform.commons.message.broadcast.manager;

import cc.wdev.platform.commons.message.broadcast.BroadcastSender;
import cc.wdev.platform.commons.message.broadcast.rabbit.RabbitBroadcastSender;

/**
 * @author elvea
 */
public interface BroadcastManager {

    BroadcastSender getSender();

    RabbitBroadcastSender getRabbitSender();

}
