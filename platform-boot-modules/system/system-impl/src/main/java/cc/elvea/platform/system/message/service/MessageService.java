package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.message.enums.MessageStatusEnum;
import cc.elvea.platform.system.message.model.entity.MessageEntity;

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
