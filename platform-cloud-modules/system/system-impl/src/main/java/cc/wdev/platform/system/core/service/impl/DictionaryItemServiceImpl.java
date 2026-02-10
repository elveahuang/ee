package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.DictionaryItemEntity;
import cc.wdev.platform.system.core.mapper.DictionaryItemMapper;
import cc.wdev.platform.system.core.service.DictionaryItemService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryItemService
 */
@Service
public class DictionaryItemServiceImpl extends BaseCachingEntityService<DictionaryItemEntity, Long, DictionaryItemMapper> implements DictionaryItemService {
}
