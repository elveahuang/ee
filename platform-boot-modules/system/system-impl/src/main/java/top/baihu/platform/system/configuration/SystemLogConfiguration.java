package top.baihu.platform.system.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.baihu.platform.commons.core.log.domain.ApplicationLogDto;
import top.baihu.platform.commons.core.log.domain.OperationLogDto;
import top.baihu.platform.commons.core.log.domain.UrlLogDto;
import top.baihu.platform.commons.core.log.store.LogStore;
import top.baihu.platform.system.core.manager.LogManager;

/**
 * @author elvea
 */
@Slf4j
@Configuration
public class SystemLogConfiguration {

    @Bean
    public LogStore logStore(LogManager logManager) {
        return new LogStore() {
            @Override
            public void saveApplicationLog(ApplicationLogDto dto) throws Exception {
                logManager.saveApplicationLog(dto);
            }

            @Override
            public void saveOperationLog(OperationLogDto dto) throws Exception {
                logManager.saveOperationLog(dto);
            }

            @Override
            public void saveUrlLog(UrlLogDto dto) throws Exception {
                logManager.saveUrlLogLog(dto);
            }
        };
    }

}
