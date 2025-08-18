package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseEntityService;
import cc.wdev.platform.system.core.domain.entity.LabelEntity;
import cc.wdev.platform.system.core.mapper.LangLabelMapper;
import cc.wdev.platform.system.core.service.LabelService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see LabelService
 */
@Service
public class LabelServiceImpl extends BaseEntityService<LabelEntity, Long, LangLabelMapper> implements LabelService {

    @Override
    public LabelEntity findByCode(String code) {
        return lambdaQueryWrapper().eq(LabelEntity::getCode, code).one();
    }

}
