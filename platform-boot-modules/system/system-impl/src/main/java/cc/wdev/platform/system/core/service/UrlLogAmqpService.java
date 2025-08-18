package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.core.log.domain.UrlLogDto;
import cc.wdev.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface UrlLogAmqpService extends AmqpService<UrlLogDto> {
}
