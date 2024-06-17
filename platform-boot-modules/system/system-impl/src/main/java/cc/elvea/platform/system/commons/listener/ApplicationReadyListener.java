package cc.elvea.platform.system.commons.listener;

import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.job.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        log.info("ApplicationReadyListener.onApplicationEvent...");

        // 重新加载定时任务
        initJobs();
    }

    private void initJobs() {
        JobService taskService = SpringUtils.getBean(JobService.class);
        taskService.initJobs();
    }

}
