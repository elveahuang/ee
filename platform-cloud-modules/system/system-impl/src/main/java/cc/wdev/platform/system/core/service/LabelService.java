package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.core.domain.entity.LabelEntity;

/**
 * LabelManager
 *
 * @author elvea
 */
public interface LabelService extends EntityService<LabelEntity, Long> {

    LabelEntity findByCode(String code);

}
