package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.mybatis.service.BaseEntityService;
import cc.elvea.platform.system.core.mapper.LangTypeMapper;
import cc.elvea.platform.system.core.model.entity.LangEntity;
import cc.elvea.platform.system.core.service.LangService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see LangService
 */
@Service
public class LangServiceImpl extends BaseEntityService<LangEntity, Long, LangTypeMapper> implements LangService {
}
