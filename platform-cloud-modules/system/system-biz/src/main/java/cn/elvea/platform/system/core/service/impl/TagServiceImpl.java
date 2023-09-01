package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseCachingEntityService;
import cn.elvea.platform.system.core.model.entity.TagEntity;
import cn.elvea.platform.system.core.mapper.TagMapper;
import cn.elvea.platform.system.core.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see TagService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Service
public class TagServiceImpl extends BaseCachingEntityService<TagEntity, Long, TagMapper> implements TagService {
}
