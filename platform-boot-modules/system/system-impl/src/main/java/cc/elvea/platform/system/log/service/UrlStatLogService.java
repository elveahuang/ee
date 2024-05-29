package cc.elvea.platform.system.log.service;

import cc.elvea.platform.commons.logging.domain.UrlLogDto;
import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.log.model.entity.UrlStatLogEntity;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UrlStatLogService extends CachingEntityService<UrlStatLogEntity, Long> {

    UrlStatLogEntity findUrlStatLogByPath(String path);

    void saveUrlStatLog(UrlLogDto urlLog);

}
