package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseEntityService;
import cc.elvea.platform.system.core.mapper.LangLabelMapper;
import cc.elvea.platform.system.core.model.entity.LabelEntity;
import cc.elvea.platform.system.core.service.LabelService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see LabelService
 * @since 24.1.0
 */
@Service
public class LabelServiceImpl extends BaseEntityService<LabelEntity, Long, LangLabelMapper> implements LabelService {

    @Override
    public LabelEntity findByCode(String code) {
        return lambdaQuery().eq(LabelEntity::getCode, code).one();
    }

}
