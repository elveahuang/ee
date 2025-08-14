package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.UserSessionEntity;

/**
 * @author elvea
 */
public interface UserSessionService extends CachingEntityService<UserSessionEntity, Long> {

    /**
     * 获取单条会话记录
     */
    UserSessionEntity findBySessionId(String sessionId);

}
