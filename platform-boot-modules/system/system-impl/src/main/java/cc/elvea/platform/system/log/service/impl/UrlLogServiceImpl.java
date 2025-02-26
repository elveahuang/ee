package cc.elvea.platform.system.log.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseEntityService;
import cc.elvea.platform.system.log.model.entity.UrlLogEntity;
import cc.elvea.platform.system.log.repository.UrlLogRepository;
import cc.elvea.platform.system.log.service.UrlLogService;
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
