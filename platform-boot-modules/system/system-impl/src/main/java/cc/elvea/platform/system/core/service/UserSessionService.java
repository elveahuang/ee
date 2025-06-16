package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;

/**
 * @author elvea
 */
public interface UserSessionService extends CachingEntityService<UserSessionEntity, Long> {

    /**
     * 获取单条会话记录
     */
    UserSessionEntity findBySessionId(String sessionId);

}
