package cc.elvea.platform.system.message.service;

import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.message.model.entity.MessageContentEntity;

import java.util.List;

/**
 * @author elvea
 */
public interface MessageContentService extends EntityService<MessageContentEntity, Long> {

    List<MessageContentEntity> findByMessage(Long messageId);

    void success(Long id, String resp);

    void fail(Long id, String resp);

    void fail(Long id, String resp, String exception);

}
