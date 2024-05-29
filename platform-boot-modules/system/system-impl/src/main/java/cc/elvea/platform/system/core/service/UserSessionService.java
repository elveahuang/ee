package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UserSessionService extends CachingEntityService<UserSessionEntity, Long> {

    /**
     * 获取单条会话记录
     */
    UserSessionEntity findBySessionId(String sessionId);

}
