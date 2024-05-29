package cc.elvea.platform.commons.logging.store;

import cc.elvea.platform.commons.logging.domain.OperationLogDto;

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

}
