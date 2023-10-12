package cn.elvea.platform.commons.core.log.store;

import cn.elvea.platform.commons.core.log.dto.OperationLogDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface LogStore {

    /**
     * 保存操作日志
     *
     * @param dto {@link OperationLogDto}
     */
    void saveLog(OperationLogDto dto) throws Exception;

}
