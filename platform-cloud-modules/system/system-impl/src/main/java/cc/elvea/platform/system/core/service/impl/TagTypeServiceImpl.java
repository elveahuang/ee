package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.mapper.TagTypeMapper;
import cc.elvea.platform.system.core.model.entity.TagTypeEntity;
import cc.elvea.platform.system.core.service.TagTypeService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagTypeService
 * @see BaseCachingEntityService
 */
@Service
public class TagTypeServiceImpl extends BaseCachingEntityService<TagTypeEntity, Long, TagTypeMapper> implements TagTypeService {
}
