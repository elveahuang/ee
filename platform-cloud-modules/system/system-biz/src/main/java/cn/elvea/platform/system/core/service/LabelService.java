package cn.elvea.platform.system.core.service;

import cn.elvea.platform.commons.core.service.EntityService;
import cn.elvea.platform.system.core.model.entity.LabelEntity;

/**
 * LabelManager
 *
 * @author elvea
 * @since 0.0.1
 */
public interface LabelService extends EntityService<LabelEntity, Long> {

    LabelEntity findByCode(String code);

}
