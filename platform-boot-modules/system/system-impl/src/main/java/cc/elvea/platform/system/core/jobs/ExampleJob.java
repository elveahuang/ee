package cc.elvea.platform.system.core.jobs;

import cc.elvea.platform.commons.core.quartz.QuartzJob;
import lombok.extern.slf4j.Slf4j;

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
