package top.baihu.platform.system.core.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.core.quartz.QuartzJobInfo;
import top.baihu.platform.commons.core.quartz.QuartzJobManager;
import top.baihu.platform.commons.core.quartz.QuartzJobScheduleType;
import top.baihu.platform.commons.data.jpa.service.BaseCachingEntityService;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.system.core.domain.entity.JobEntity;
import top.baihu.platform.system.core.repository.JobRepository;
import top.baihu.platform.system.core.service.JobService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author elvea
 * @see JobService
 * @see BaseCachingEntityService
 */
@Slf4j
@Service
@AllArgsConstructor
public class JobServiceImpl extends BaseCachingEntityService<JobEntity, Long, JobRepository> implements JobService {

    private final QuartzJobManager quartzJobManager;

    @Override
    public void init() {
        List<JobEntity> jobList = this.findAll();
        if (CollectionUtils.isNotEmpty(jobList)) {
            for (JobEntity jobEntity : jobList) {
                QuartzJobInfo jobInfo = new QuartzJobInfo();
                jobInfo.setKey(jobEntity.getType());
                jobInfo.setClassName(jobEntity.getClassname());
                jobInfo.setScheduleType(QuartzJobScheduleType.PERIOD);
                jobInfo.setUnit(TimeUnit.MINUTES);
                jobInfo.setPeriod(jobEntity.getPeriod());
                quartzJobManager.schedule(jobInfo, new Date());
            }
        }
    }

}
