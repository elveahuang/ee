package top.baihu.platform.system.core.service.impl;

import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseEntityService;
import top.baihu.platform.system.core.domain.entity.LabelEntity;
import top.baihu.platform.system.core.mapper.LangLabelMapper;
import top.baihu.platform.system.core.service.LabelService;

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
