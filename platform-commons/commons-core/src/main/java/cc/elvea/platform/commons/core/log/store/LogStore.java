package cc.elvea.platform.commons.core.log.store;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface LogStore {

    /**
     * 保存操作日志
     *
     * @param dto {@link OperationLogDto}
     */
    void saveOperationLog(OperationLogDto dto) throws Exception;

    /**
     * 保存链接日志
     *
     * @param dto {@link UrlLogDto}
     */
    void saveUrlLog(UrlLogDto dto) throws Exception;

}
