package cc.wdev.platform.system.core.listener;

import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.core.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author elvea
 */
@Slf4j
public class ApplicationReadyListener implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        log.info("ApplicationReadyListener.onApplicationEvent");

        // 重新加载定时任务
        initJobs();
    }

    private void initJobs() {
        JobService taskService = SpringUtils.getBean(JobService.class);
        taskService.init();
    }

}
