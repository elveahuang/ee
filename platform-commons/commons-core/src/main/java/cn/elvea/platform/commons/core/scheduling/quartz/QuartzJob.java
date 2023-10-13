package cn.elvea.platform.commons.core.scheduling.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@DisallowConcurrentExecution
public abstract class QuartzJob implements Job {

    protected JobExecutionContext jobContext = null;

    /**
     * 执行定时任务
     */
    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        try {
            this.jobContext = jobContext;

            JobDetail jobDetail = jobContext.getJobDetail();

            Trigger trigger = jobContext.getTrigger();

            log.debug("Job [{}] trigger [{}] exec start.", jobDetail.getKey(), trigger.getKey());
            execute();
            log.debug("Job [{}] trigger [{}] exec end.", jobDetail.getKey(), trigger.getKey());
            log.debug("Job [{}] trigger [{}] getFireTime - {}", jobDetail.getKey(), trigger.getKey(), jobContext.getFireTime());
            log.debug("Job [{}] trigger [{}] getNextFireTime - {}", jobDetail.getKey(), trigger.getKey(), jobContext.getNextFireTime());
            log.debug("Job [{}] trigger [{}] getPreviousFireTime - {}", jobDetail.getKey(), trigger.getKey(), jobContext.getPreviousFireTime());
            log.debug("Job [{}] trigger [{}] getScheduledFireTime - {}", jobDetail.getKey(), trigger.getKey(), jobContext.getScheduledFireTime());
            log.debug("Job [{}] trigger [{}] getRefireCount - {}", jobDetail.getKey(), trigger.getKey(), jobContext.getRefireCount());
        } catch (Exception e) {
            log.error("Job exec error.", e);
        }
    }

    /**
     * 获取任务上下文
     */
    protected JobExecutionContext getContext() {
        return jobContext;
    }

    /**
     * 子类中实现任务的操作
     */
    protected void execute() throws Exception {
        //
    }

}
