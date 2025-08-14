package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.core.domain.entity.AnnouncementEntity;

/**
 * @author elvea
 * @see EntityService
 * @see CachingEntityService
 */
public interface AnnouncementService extends CachingEntityService<AnnouncementEntity, Long> {
}
