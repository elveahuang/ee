package cc.elvea.platform.system.configuration;

import cc.elvea.platform.commons.core.log.domain.ApplicationLogDto;
import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.core.log.store.LogStore;
import cc.elvea.platform.system.commons.manager.LogManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
