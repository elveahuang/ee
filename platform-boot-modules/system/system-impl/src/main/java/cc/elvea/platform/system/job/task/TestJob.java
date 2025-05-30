package cc.elvea.platform.system.job.task;

import cc.elvea.platform.commons.core.quartz.QuartzJob;
import lombok.extern.slf4j.Slf4j;


/**
 * @author elvea
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
