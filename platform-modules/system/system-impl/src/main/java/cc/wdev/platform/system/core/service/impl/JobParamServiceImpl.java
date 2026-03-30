package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.JobParamEntity;
import cc.wdev.platform.system.core.repository.JobParamRepository;
import cc.wdev.platform.system.core.service.JobParamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see JobParamService
 * @see BaseCachingEntityService
 */
@Slf4j
@AllArgsConstructor
@Service
public class JobParamServiceImpl extends BaseCachingEntityService<JobParamEntity, Long, JobParamRepository> implements JobParamService {
}
