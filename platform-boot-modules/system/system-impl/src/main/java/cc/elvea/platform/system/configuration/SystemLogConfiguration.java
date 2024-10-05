package cc.elvea.platform.system.configuration;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.core.log.store.LogStore;
import cc.elvea.platform.system.log.api.LogApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration
public class SystemLogConfiguration {

    @Bean
    public LogStore logStore(LogApi logApi) {
        return new LogStore() {
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
