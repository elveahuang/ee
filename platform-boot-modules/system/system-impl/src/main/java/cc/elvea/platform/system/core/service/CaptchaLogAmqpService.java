package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.elvea.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface CaptchaLogAmqpService extends AmqpService<CaptchaLogDto> {
}
