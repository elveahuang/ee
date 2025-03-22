package cc.elvea.platform.commons.core.quartz;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @author elvea
 */
@Slf4j
@AllArgsConstructor
public class QuartzJobManager {

    private final Scheduler scheduler;

    /**
     * 启动任务
     *
     * @param job 任务
     */
    @SuppressWarnings("unchecked")
    public void schedule(QuartzJobInfo job, Date curDate) {
        try {
            log.info("Trying to schedule task [{}][{}].", job.getKey(), job.getClassName());

            // 获取任务类实例
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(job.getClassName());

            JobKey jobKey = JobKey.jobKey(job.getKey());
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getKey());

            TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger().withIdentity(triggerKey).forJob(jobKey).startAt(curDate);
            if (QuartzJobScheduleType.DAILY.equals(job.getScheduleType())) {
                builder.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(job.getHour(), job.getMinute()));
            } else if (QuartzJobScheduleType.PERIOD.equals(job.getScheduleType())) {
                SimpleScheduleBuilder scheduleBuilder = simpleSchedule().repeatForever();
                if (TimeUnit.HOURS.equals(job.getUnit())) {
                    builder.withSchedule(scheduleBuilder.withIntervalInHours(job.getPeriod()));
                } else if (TimeUnit.MINUTES.equals(job.getUnit())) {
                    builder.withSchedule(scheduleBuilder.withIntervalInMinutes(job.getPeriod()));
                } else if (TimeUnit.SECONDS.equals(job.getUnit())) {
                    builder.withSchedule(scheduleBuilder.withIntervalInSeconds(job.getPeriod()));
                } else {
                    log.info("Schedule task [{}][{}]. failed. Invalid arg unit.", job.getKey(), job.getClassName());
                    throw new SchedulerException("Invalid arg unit.");
                }
            } else if (QuartzJobScheduleType.CRON.equals(job.getScheduleType())) {
                if (CronExpression.isValidExpression(job.getCron())) {
                    builder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCron()));
                } else {
                    log.info("Schedule task [{}][{}]. failed. Invalid cron expression [{}].", job.getKey(), job.getClassName(), job.getCron());
                    throw new SchedulerException("Invalid cron expression.");
                }
            } else {
                log.info("Schedule task [{}][{}]. failed. Invalid arg type.", job.getKey(), job.getClassName());
                throw new SchedulerException("Invalid arg type.");
            }
            Trigger trigger = builder.build();

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(jobKey).build();
            if (scheduler.checkExists(triggerKey)) {
                scheduler.rescheduleJob(triggerKey, trigger);
                log.info("Schedule task [{}][{}]. done. rescheduled.", job.getKey(), job.getClassName());
            } else {
                scheduler.scheduleJob(jobDetail, trigger);
                log.info("Schedule task [{}][{}]. done. scheduled.", job.getKey(), job.getClassName());
            }
        } catch (Exception e) {
            log.error("Schedule task [{}][{}]. failed.", job.getKey(), job.getClassName(), e);
        }
    }

    /**
     * 运行一次任务
     *
     * @param job 任务
     */
    public void run(QuartzJobInfo job) {
        JobKey jobKey = JobKey.jobKey(job.getKey());
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("Failed to run scheduler task once.", e);
        }
    }

    /**
     * 删除任务
     *
     * @param job 任务
     */
    public void delete(QuartzJobInfo job) {
        try {
            log.info("Trying to delete task [{}][{}].", job.getKey(), job.getClassName());

            JobKey jobKey = JobKey.jobKey(job.getKey());
            if (scheduler.checkExists(jobKey)) {
                scheduler.deleteJob(jobKey);
                log.info("Trying to delete task [{}][{}]. done.", job.getKey(), job.getClassName());
            } else {
                log.info("Trying to delete task [{}][{}]. not exist.", job.getKey(), job.getClassName());
            }
        } catch (SchedulerException e) {
            log.info("Trying to delete task [{}][{}]. failed.", job.getKey(), job.getClassName());
        }
    }

    /**
     * 暂停任务
     *
     * @param job 任务
     */
    public void pause(QuartzJobInfo job) {
        try {
            log.info("Trying to pause task [{}][{}].", job.getKey(), job.getClassName());

            JobKey jobKey = JobKey.jobKey(job.getKey());
            if (scheduler.checkExists(jobKey)) {
                scheduler.pauseJob(jobKey);
                log.info("Trying to pause task [{}][{}]. done.", job.getKey(), job.getClassName());
            } else {
                log.info("Trying to pause task [{}][{}]. not exist.", job.getKey(), job.getClassName());
            }
        } catch (SchedulerException e) {
            log.error("Trying to delete task [{}][{}]. failed.", job.getKey(), job.getClassName());
        }
    }

    /**
     * 恢复任务
     *
     * @param job 任务
     */
    public void resume(QuartzJobInfo job) {
        try {
            log.info("Trying to resume task [{}][{}].", job.getKey(), job.getClassName());

            JobKey jobKey = JobKey.jobKey(job.getKey());
            if (scheduler.checkExists(jobKey)) {
                scheduler.resumeJob(jobKey);
                log.info("Trying to resume task [{}][{}]. done.", job.getKey(), job.getClassName());
            } else {
                log.info("Trying to resume task [{}][{}]. not exist.", job.getKey(), job.getClassName());
            }
        } catch (SchedulerException e) {
            log.error("Trying to resume task [{}][{}]. failed.", job.getKey(), job.getClassName());
        }
    }

    /**
     * 清空所有任务
     */
    private void clear() {
        try {
            log.info("Clear scheduler. start.");
            scheduler.clear();
            log.info("Clear scheduler. done.");
        } catch (SchedulerException e) {
            log.error("Clear scheduler failed.", e);
        }
    }

}
