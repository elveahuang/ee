package top.baihu.platform.system.core.jobs;

import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.core.quartz.QuartzJob;

/**
 * @author elvea
 */
@Slf4j
public class ExampleJob extends QuartzJob {

    /**
     * @see QuartzJob#execute()
     */
    protected void execute() {
        log.info("TestJob.execute...");
    }

}
