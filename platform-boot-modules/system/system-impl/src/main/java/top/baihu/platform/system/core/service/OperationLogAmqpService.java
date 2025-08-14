package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.core.log.domain.OperationLogDto;
import top.baihu.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface OperationLogAmqpService extends AmqpService<OperationLogDto> {
}
