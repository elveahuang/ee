package cc.elvea.platform.system.log.service;

import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.elvea.platform.commons.message.amqp.AmqpService;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface CaptchaLogAmqpService extends AmqpService<CaptchaLogDto> {
}
