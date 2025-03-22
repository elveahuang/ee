package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.core.model.entity.LabelEntity;

/**
 * LabelManager
 *
 * @author elvea
 */
public interface LabelService extends EntityService<LabelEntity, Long> {

    LabelEntity findByCode(String code);

}
