package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.entity.UserSessionEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface UserSessionService extends CachingEntityService<UserSessionEntity, Long> {
}
