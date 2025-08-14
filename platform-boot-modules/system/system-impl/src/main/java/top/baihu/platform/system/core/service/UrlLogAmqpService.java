package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.core.log.domain.UrlLogDto;
import top.baihu.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface UrlLogAmqpService extends AmqpService<UrlLogDto> {
}
