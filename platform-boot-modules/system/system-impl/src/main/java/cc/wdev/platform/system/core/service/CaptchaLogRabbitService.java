package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.wdev.platform.commons.message.rabbit.RabbitService;

/**
 * @author elvea
 */
public interface CaptchaLogRabbitService extends RabbitService<CaptchaLogDto> {
}
