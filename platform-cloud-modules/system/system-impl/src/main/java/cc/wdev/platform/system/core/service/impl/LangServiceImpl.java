package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.mybatis.service.BaseEntityService;
import cc.wdev.platform.system.core.domain.entity.LangEntity;
import cc.wdev.platform.system.core.mapper.LangTypeMapper;
import cc.wdev.platform.system.core.service.LangService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see LangService
 */
@Service
public class LangServiceImpl extends BaseEntityService<LangEntity, Long, LangTypeMapper> implements LangService {
}
