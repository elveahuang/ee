package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.model.entity.TagTypeEntity;
import cn.elvea.platform.system.core.mapper.TagTypeMapper;
import cn.elvea.platform.system.core.service.TagTypeService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagTypeService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Service
public class TagTypeServiceImpl extends BaseCachingEntityService<TagTypeEntity, Long, TagTypeMapper> implements TagTypeService {
}
