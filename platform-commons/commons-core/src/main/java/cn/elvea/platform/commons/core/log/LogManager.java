package cn.elvea.platform.commons.core.log;

import cn.elvea.platform.commons.core.log.dto.OperationLogDto;
import cn.elvea.platform.commons.core.log.store.DefaultLogStore;
import cn.elvea.platform.commons.core.log.store.LogStore;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public class LogManager {

    private List<LogStore> providers;

    public LogManager() {
        this(Lists.newArrayList(new DefaultLogStore()));
    }

    public LogManager(List<LogStore> providers) {
        this.providers = providers;
    }

    @Async
    public void saveLog(OperationLogDto dto) throws Exception {
        if (CollectionUtils.isNotEmpty(this.providers)) {
            for (LogStore provider : this.providers) {
                provider.saveLog(dto);
            }
        }
    }

    /**
     * 作为第一个日志服务
     */
    public void first(LogStore loggingProvider) {
        this.providers.add(0, loggingProvider);
    }

    /**
     * 作为最后一个日志服务
     */
    public void last(LogStore loggingProvider) {
        this.providers.add(loggingProvider);
    }

    /**
     * 只保留参数中的日志服务
     */
    public void only(LogStore loggingProvider) {
        this.providers = Lists.newArrayList(loggingProvider);
    }

}
