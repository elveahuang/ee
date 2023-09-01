package cn.elvea.platform.system.core.service.impl;

import cn.elvea.platform.commons.core.data.mybatis.service.BaseEntityService;
import cn.elvea.platform.system.core.model.entity.LangEntity;
import cn.elvea.platform.system.core.mapper.LangTypeMapper;
import cn.elvea.platform.system.core.service.LangService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see LangService
 * @since 0.0.1
 */
@Service
public class LangServiceImpl extends BaseEntityService<LangEntity, Long, LangTypeMapper> implements LangService {
}
