package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.domain.entity.AnnouncementEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface AnnouncementService extends CachingEntityService<AnnouncementEntity, Long> {
}
