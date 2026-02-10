package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.core.log.domain.ApplicationLogDto;
import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.commons.core.log.domain.UrlLogDto;
import cc.wdev.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.wdev.platform.system.core.service.CaptchaLogRabbitService;
import cc.wdev.platform.system.core.service.OperationLogRabbitService;
import cc.wdev.platform.system.core.service.UrlLogRabbitService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class LogApiImpl implements LogApi {

    private final CaptchaLogRabbitService captchaLogRabbitService;

    private final OperationLogRabbitService operationLogRabbitService;

    private final UrlLogRabbitService urlLogRabbitService;

    @Override
    public void saveApplicationLog(ApplicationLogDto dto) {
    }

    @Override
    public void saveUrlLogLog(UrlLogDto captchaLog) throws Exception {
        this.urlLogRabbitService.send(captchaLog);
    }

    @Override
    public void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception {
        this.captchaLogRabbitService.send(captchaLog);
    }

    @Override
    public void saveOperationLog(OperationLogDto operationLog) throws Exception {
        this.operationLogRabbitService.send(operationLog);
    }

}
