package cc.wdev.platform.system.log.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseEntityService;
import cc.wdev.platform.system.log.domain.entity.UrlLogEntity;
import cc.wdev.platform.system.log.repository.UrlLogRepository;
import cc.wdev.platform.system.log.service.UrlLogService;
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
