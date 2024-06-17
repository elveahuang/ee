package cc.elvea.platform.system.job.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.job.quartz.QuartzJobInfo;
import cc.elvea.platform.commons.job.quartz.QuartzJobManager;
import cc.elvea.platform.commons.job.quartz.QuartzJobScheduleType;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.system.job.model.entity.JobEntity;
import cc.elvea.platform.system.job.repository.JobRepository;
import cc.elvea.platform.system.job.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author elvea
 * @see JobService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
@Service
public class JobServiceImpl extends BaseCachingEntityService<JobEntity, Long, JobRepository> implements JobService {

    private final QuartzJobManager quartzJobManager;

    @Override
    public void initJobs() {
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
