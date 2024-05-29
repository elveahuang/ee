package cc.elvea.platform.system.log.service;

import cc.elvea.platform.commons.logging.domain.OperationLogDto;
import cc.elvea.platform.commons.message.amqp.AmqpService;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface OperationLogAmqpService extends AmqpService<OperationLogDto> {
}
