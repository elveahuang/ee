package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.service.EntityService;
import top.baihu.platform.system.core.domain.entity.LabelEntity;

/**
 * LabelManager
 *
 * @author elvea
 */
public interface LabelService extends EntityService<LabelEntity, Long> {

    LabelEntity findByCode(String code);

}
