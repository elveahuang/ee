package cn.elvea.platform.system.message.api;

import cn.elvea.platform.system.message.model.dto.CreateMessageDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageApi {

    /**
     * 创建消息
     */
    Long createMessage(CreateMessageDto message) throws Exception;

    /**
     * 发送消息
     */
    void sendMessage(Long id) throws Exception;

    /**
     * 发送消息
     */
    void sendMessage(Long id, boolean force) throws Exception;

}
