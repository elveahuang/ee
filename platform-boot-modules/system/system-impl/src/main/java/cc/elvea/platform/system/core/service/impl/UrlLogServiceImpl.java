package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseEntityService;
import cc.elvea.platform.system.core.model.entity.UrlLogEntity;
import cc.elvea.platform.system.core.repository.UrlLogRepository;
import cc.elvea.platform.system.core.service.UrlLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UrlLogService
 * @see BaseEntityService
 */
@Slf4j
@Service
@AllArgsConstructor
public class UrlLogServiceImpl extends BaseEntityService<UrlLogEntity, Long, UrlLogRepository> implements UrlLogService {
}
