package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.message.model.dto.MessageRecipientDto;
import cc.elvea.platform.system.message.model.dto.MessageSenderDto;
import cc.elvea.platform.system.message.model.entity.MessageUserEntity;

import java.util.List;

/**
 * @author elvea
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
