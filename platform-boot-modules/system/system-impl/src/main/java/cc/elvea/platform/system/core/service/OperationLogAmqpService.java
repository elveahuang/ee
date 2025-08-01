package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface OperationLogAmqpService extends AmqpService<OperationLogDto> {
}
