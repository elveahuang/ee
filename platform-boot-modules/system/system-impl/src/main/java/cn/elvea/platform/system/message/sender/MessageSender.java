package cn.elvea.platform.system.message.sender;

import cn.elvea.platform.system.message.model.dto.SendMessageDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageSender {

    default void send(SendMessageDto message) {
    }

}
