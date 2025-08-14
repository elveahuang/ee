package top.baihu.platform.system.message.service;

import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.message.domain.entity.MessageEntity;
import top.baihu.platform.system.message.enums.MessageStatusEnum;

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
