package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.mapper.TagMapper;
import cc.elvea.platform.system.core.model.entity.TagEntity;
import cc.elvea.platform.system.core.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagService
 * @see BaseCachingEntityService
 */
@Service
public class TagServiceImpl extends BaseCachingEntityService<TagEntity, Long, TagMapper> implements TagService {
}
