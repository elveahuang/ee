package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.message.model.entity.MessageContentEntity;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageContentService extends EntityService<MessageContentEntity, Long> {

    /**
     * 获取消息内容
     */
    List<MessageContentEntity> findByMessage(Long messageId);

}
