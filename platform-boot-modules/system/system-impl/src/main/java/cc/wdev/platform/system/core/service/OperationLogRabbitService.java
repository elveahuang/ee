package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.commons.message.rabbit.RabbitService;

/**
 * @author elvea
 */
public interface OperationLogRabbitService extends RabbitService<OperationLogDto> {
}
