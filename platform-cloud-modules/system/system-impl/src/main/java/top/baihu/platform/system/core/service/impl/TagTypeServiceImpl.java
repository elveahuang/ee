package top.baihu.platform.system.core.service.impl;

import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.TagTypeEntity;
import top.baihu.platform.system.core.mapper.TagTypeMapper;
import top.baihu.platform.system.core.service.TagTypeService;

/**
 * @author elvea
 * @see TagTypeService
 * @see BaseCachingEntityService
 */
@Service
public class TagTypeServiceImpl extends BaseCachingEntityService<TagTypeEntity, Long, TagTypeMapper> implements TagTypeService {
}
