package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.mapper.DictionaryRelationMapper;
import cc.elvea.platform.system.core.model.entity.DictionaryRelationEntity;
import cc.elvea.platform.system.core.service.DictionaryRelationService;
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
