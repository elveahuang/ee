package cn.elvea.platform.system.log.service.impl;

import cn.elvea.platform.commons.core.data.jpa.service.BaseCachingEntityService;
import cn.elvea.platform.system.log.model.entity.CaptchaLogEntity;
import cn.elvea.platform.system.log.repository.CaptchaLogRepository;
import cn.elvea.platform.system.log.service.CaptchaLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see CaptchaLogService
 * @see BaseCachingEntityService
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
@Service
public class CaptchaLogServiceImpl
        extends BaseCachingEntityService<CaptchaLogEntity, Long, CaptchaLogRepository>
        implements CaptchaLogService {
}