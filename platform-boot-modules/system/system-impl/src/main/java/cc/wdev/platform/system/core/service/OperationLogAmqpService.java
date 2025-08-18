package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface OperationLogAmqpService extends AmqpService<OperationLogDto> {
}
