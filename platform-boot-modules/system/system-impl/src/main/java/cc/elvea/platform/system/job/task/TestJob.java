package cc.elvea.platform.system.job.task;

import cc.elvea.platform.commons.core.quartz.QuartzJob;
import lombok.extern.slf4j.Slf4j;


/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class TestJob extends QuartzJob {

    /**
     * 子类中实现任务的操作
     */
    protected void execute() {
        log.info("TestJob.execute...");
    }

}
