package cc.elvea.platform.commons.core.log.store;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class DefaultLogStore implements LogStore {

    /**
     * @see LogStore#saveOperationLog(OperationLogDto)
     */
    @Async
    @Override
    public void saveOperationLog(OperationLogDto dto) {
        try {
            log.info(dto.toString());
        } catch (Exception e) {
            log.error("Save opt log error.", e);
        }
    }

    /**
     * @see LogStore#saveUrlLog(UrlLogDto)
     */
    @Async
    @Override
    public void saveUrlLog(UrlLogDto dto) {
        try {
            log.info(dto.toString());
        } catch (Exception e) {
            log.error("Save url log error.", e);
        }
    }

}
