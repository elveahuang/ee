package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.wdev.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface CaptchaLogAmqpService extends AmqpService<CaptchaLogDto> {
}
