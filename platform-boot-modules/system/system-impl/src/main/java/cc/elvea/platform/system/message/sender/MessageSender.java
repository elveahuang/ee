package cc.elvea.platform.system.message.sender;

import cc.elvea.platform.system.message.domain.dto.SendMessageDto;

/**
 * @author elvea
 */
public interface MessageSender {

    default void send(SendMessageDto message) {
    }

}
