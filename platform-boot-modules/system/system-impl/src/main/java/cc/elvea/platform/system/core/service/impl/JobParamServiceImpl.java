package cc.elvea.platform.system.core.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.system.core.domain.entity.JobParamEntity;
import cc.elvea.platform.system.core.repository.JobParamRepository;
import cc.elvea.platform.system.core.service.JobParamService;
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
