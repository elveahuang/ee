package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.message.model.dto.MessageRecipientDto;
import cn.elvea.platform.system.message.model.dto.MessageSenderDto;
import cn.elvea.platform.system.message.model.entity.MessageUserEntity;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageUserService extends EntityService<MessageUserEntity, Long> {

    /**
     * 获取消息用户
     */
    List<MessageUserEntity> findByMessage(Long messageId);

    /**
     * 获取消息发送人
     */
    MessageSenderDto getSender(Long id);

    /**
     * 获取消息发送人
     */
    MessageSenderDto getSender(MessageUserEntity entity);

    /**
     * 获取消息收件人
     */
    MessageRecipientDto getRecipient(Long id);

    /**
     * 获取消息收件人
     */
    MessageRecipientDto getRecipient(MessageUserEntity entity);

}
