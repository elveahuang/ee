package cc.wdev.platform.system.message.service;

import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.message.domain.entity.MessageEntity;
import cc.wdev.platform.system.message.enums.MessageStatusEnum;

import java.util.List;

/**
 * @author elvea
 */
public interface MessageService extends EntityService<MessageEntity, Long> {

    /**
     * 根据发送状态获取消息记录
     */
    List<MessageEntity> findByStatus(List<MessageStatusEnum> statusList);

}
