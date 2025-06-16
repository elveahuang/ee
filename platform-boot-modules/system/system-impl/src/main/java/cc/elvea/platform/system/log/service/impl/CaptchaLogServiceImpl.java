package cc.elvea.platform.system.log.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import cc.elvea.platform.system.log.repository.CaptchaLogRepository;
import cc.elvea.platform.system.log.service.CaptchaLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see CaptchaLogService
 * @see BaseCachingEntityService
 */
@Slf4j
@AllArgsConstructor
@Service
public class CaptchaLogServiceImpl extends BaseCachingEntityService<CaptchaLogEntity, Long, CaptchaLogRepository> implements CaptchaLogService {
}