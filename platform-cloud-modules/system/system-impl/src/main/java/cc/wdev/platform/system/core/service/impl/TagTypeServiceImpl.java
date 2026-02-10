package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.TagTypeEntity;
import cc.wdev.platform.system.core.mapper.TagTypeMapper;
import cc.wdev.platform.system.core.service.TagTypeService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagTypeService
 * @see BaseCachingEntityService
 */
@Service
public class TagTypeServiceImpl extends BaseCachingEntityService<TagTypeEntity, Long, TagTypeMapper> implements TagTypeService {
}
