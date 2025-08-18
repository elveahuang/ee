package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.UserSessionEntity;

/**
 * @author elvea
 */
public interface UserSessionService extends CachingEntityService<UserSessionEntity, Long> {

    /**
     * 获取单条会话记录
     */
    UserSessionEntity findBySessionId(String sessionId);

}
