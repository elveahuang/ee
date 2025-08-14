package top.baihu.platform.system.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.core.service.JobService;

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
