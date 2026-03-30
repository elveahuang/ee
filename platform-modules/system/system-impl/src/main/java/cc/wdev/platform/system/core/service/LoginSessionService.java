package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.LoginSessionEntity;

/**
 * @author elvea
 */
public interface LoginSessionService extends CachingEntityService<LoginSessionEntity, Long> {

    /**
     * 获取单条会话记录
     */
    LoginSessionEntity findBySessionId(String sessionId);

}
