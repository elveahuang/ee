package top.baihu.platform.system.message.sender;

import top.baihu.platform.system.message.domain.dto.SendMessageDto;

/**
 * @author elvea
 */
public interface MessageSender {

    default void send(SendMessageDto message) {
    }

}
