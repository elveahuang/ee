package top.baihu.platform.system.core.service;

import top.baihu.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import top.baihu.platform.commons.message.rabbit.AmqpService;

/**
 * @author elvea
 */
public interface CaptchaLogAmqpService extends AmqpService<CaptchaLogDto> {
}
