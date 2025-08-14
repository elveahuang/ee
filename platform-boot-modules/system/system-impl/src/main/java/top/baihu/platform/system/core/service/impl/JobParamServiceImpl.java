package top.baihu.platform.system.core.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.system.core.domain.entity.JobParamEntity;
import top.baihu.platform.system.core.repository.JobParamRepository;
import top.baihu.platform.system.core.service.JobParamService;

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
