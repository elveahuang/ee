package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.core.domain.entity.UserSessionEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface UserSessionService extends CachingEntityService<UserSessionEntity, Long> {
}
