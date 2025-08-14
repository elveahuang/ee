package top.baihu.platform.commons.message.broadcast.manager;

import top.baihu.platform.commons.message.broadcast.BroadcastSender;
import top.baihu.platform.commons.message.broadcast.rabbit.RabbitBroadcastSender;

/**
 * @author elvea
 */
public interface BroadcastManager {

    BroadcastSender getSender();

    RabbitBroadcastSender getRabbitSender();

}
