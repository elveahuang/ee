package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.DictionaryRelationEntity;
import cc.wdev.platform.system.core.mapper.DictionaryRelationMapper;
import cc.wdev.platform.system.core.service.DictionaryRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see DictionaryRelationService
 */
@Service
@Slf4j
public class DictionaryRelationServiceImpl extends BaseCachingEntityService<DictionaryRelationEntity, Long, DictionaryRelationMapper>
    implements DictionaryRelationService {
}
