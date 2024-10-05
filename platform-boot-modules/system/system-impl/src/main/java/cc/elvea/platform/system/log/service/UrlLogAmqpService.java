package cc.elvea.platform.system.log.service;

import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.message.amqp.AmqpService;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface UrlLogAmqpService extends AmqpService<UrlLogDto> {
}
