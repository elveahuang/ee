package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.core.quartz.QuartzJobInfo;
import cc.wdev.platform.commons.core.quartz.QuartzJobManager;
import cc.wdev.platform.commons.core.quartz.QuartzJobScheduleType;
import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.system.core.domain.entity.JobEntity;
import cc.wdev.platform.system.core.repository.JobRepository;
import cc.wdev.platform.system.core.service.JobService;
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
