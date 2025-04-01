package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.mapper.DictionaryItemMapper;
import cc.elvea.platform.system.core.model.entity.DictionaryItemEntity;
import cc.elvea.platform.system.core.service.DictionaryItemService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryItemService
 */
@Service
public class DictionaryItemServiceImpl extends BaseCachingEntityService<DictionaryItemEntity, Long, DictionaryItemMapper> implements DictionaryItemService {
}
