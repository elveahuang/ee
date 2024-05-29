package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.system.message.model.dto.SendMessageDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface MessageSender {

    default void send(SendMessageDto message) {
    }

}
