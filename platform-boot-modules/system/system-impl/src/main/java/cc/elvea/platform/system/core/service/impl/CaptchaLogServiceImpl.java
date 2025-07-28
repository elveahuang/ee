package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.domain.entity.CaptchaLogEntity;
import cc.elvea.platform.system.core.repository.CaptchaLogRepository;
import cc.elvea.platform.system.core.service.CaptchaLogService;
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
