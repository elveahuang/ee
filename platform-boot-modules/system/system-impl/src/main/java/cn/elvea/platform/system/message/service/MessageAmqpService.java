package cn.elvea.platform.system.message.service;

import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.core.message.amqp.AmqpService;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface MessageAmqpService extends AmqpService<CaptchaLogDto> {
}
