package top.baihu.platform.system.core.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.CaptchaLogEntity;
import top.baihu.platform.system.core.repository.CaptchaLogRepository;
import top.baihu.platform.system.core.service.CaptchaLogService;

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
