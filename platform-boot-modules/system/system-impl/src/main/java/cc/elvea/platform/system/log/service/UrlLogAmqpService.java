package cc.elvea.platform.system.log.service;

import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface UrlLogAmqpService extends AmqpService<UrlLogDto> {
}
