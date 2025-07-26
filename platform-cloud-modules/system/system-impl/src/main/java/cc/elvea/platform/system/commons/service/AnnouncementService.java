package cc.elvea.platform.system.commons.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.commons.model.entity.AnnouncementEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface AnnouncementService extends CachingEntityService<AnnouncementEntity, Long> {
}
