package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.DictionaryTypeEntity;
import cc.wdev.platform.system.core.mapper.DictionaryTypeMapper;
import cc.wdev.platform.system.core.service.DictionaryTypeService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryTypeService
 */
@Service
public class DictionaryGroupServiceImpl
    extends BaseCachingEntityService<DictionaryTypeEntity, Long, DictionaryTypeMapper>
    implements DictionaryTypeService {
}
