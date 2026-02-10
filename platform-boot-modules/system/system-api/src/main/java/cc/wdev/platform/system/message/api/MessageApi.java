package cc.wdev.platform.system.message.api;

import cc.wdev.platform.system.message.domain.dto.CreateMessageDto;

/**
 * @author elvea
 */
public interface MessageApi {

    /**
     * 创建消息
     */
    Long createMessage(CreateMessageDto message) throws Exception;

    /**
     * 发送消息
     * 用于系统定时任务自动推送当前未发送的消息。
     */
    void sendMessage() throws Exception;

    /**
     * 发送消息
     */
    void sendMessage(Long id) throws Exception;

    /**
     * 发送消息
     */
    void sendMessage(Long id, boolean force) throws Exception;

    /**
     * 初始话消息模版
     */
    void initTemplate() throws Exception;

    /**
     * 同步更新消息模版
     */
    void syncTemplate(boolean force) throws Exception;

}
