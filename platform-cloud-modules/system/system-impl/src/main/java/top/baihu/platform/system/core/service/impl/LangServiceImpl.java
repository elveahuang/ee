package top.baihu.platform.system.core.service.impl;

import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.mybatis.service.BaseEntityService;
import top.baihu.platform.system.core.domain.entity.LangEntity;
import top.baihu.platform.system.core.mapper.LangTypeMapper;
import top.baihu.platform.system.core.service.LangService;

/**
 * @author elvea
 * @see LangService
 */
@Service
public class LangServiceImpl extends BaseEntityService<LangEntity, Long, LangTypeMapper> implements LangService {
}
