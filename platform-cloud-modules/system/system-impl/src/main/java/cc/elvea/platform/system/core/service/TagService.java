package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.domain.entity.TagEntity;

/**
 * @author elvea
 * @see EntityService
 */
public interface TagService extends CachingEntityService<TagEntity, Long> {
}
