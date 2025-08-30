package cc.wdev.platform.commons.core.async;

import cc.wdev.platform.commons.utils.MDCUtils;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * 异步任务装饰器
 * 1. 复制MDC上下文
 * 2. 复制Request上下文
 *
 * @author elvea
 */
public class AsyncTaskDecorator implements TaskDecorator {

    @NonNull
    @Override
    public Runnable decorate(@NonNull Runnable runnable) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return () -> {
            MDCUtils.setAsyncContext(context);
            RequestContextHolder.setRequestAttributes(attributes);

            try {
                runnable.run();
            } finally {
                MDCUtils.clear();
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }

}
