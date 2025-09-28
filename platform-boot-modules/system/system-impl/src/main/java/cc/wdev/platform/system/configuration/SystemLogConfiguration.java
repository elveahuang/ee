package cc.wdev.platform.system.configuration;

import cc.wdev.platform.commons.core.log.domain.ApplicationLogDto;
import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.commons.core.log.domain.UrlLogDto;
import cc.wdev.platform.commons.core.log.store.LogStore;
import cc.wdev.platform.system.log.api.LogApi;
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
    public LogStore logStore(LogApi logApi) {
        return new LogStore() {
            @Override
            public void saveApplicationLog(ApplicationLogDto dto) throws Exception {
                logApi.saveApplicationLog(dto);
            }

            @Override
            public void saveOperationLog(OperationLogDto dto) throws Exception {
                logApi.saveOperationLog(dto);
            }

            @Override
            public void saveUrlLog(UrlLogDto dto) throws Exception {
                logApi.saveUrlLogLog(dto);
            }
        };
    }

}
