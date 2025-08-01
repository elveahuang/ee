package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.commons.core.log.domain.ApplicationLogDto;
import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.elvea.platform.system.core.service.CaptchaLogAmqpService;
import cc.elvea.platform.system.core.service.OperationLogAmqpService;
import cc.elvea.platform.system.core.service.UrlLogAmqpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class LogManagerImpl implements LogManager {

    private final CaptchaLogAmqpService captchaLogAmqpService;

    private final OperationLogAmqpService operationLogAmqpService;

    private final UrlLogAmqpService urlLogAmqpService;

    @Override
    public void saveApplicationLog(ApplicationLogDto dto) {
    }

    @Override
    public void saveUrlLogLog(UrlLogDto captchaLog) throws Exception {
        this.urlLogAmqpService.send(captchaLog);
    }

    @Override
    public void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception {
        this.captchaLogAmqpService.send(captchaLog);
    }

    @Override
    public void saveOperationLog(OperationLogDto operationLog) throws Exception {
        this.operationLogAmqpService.send(operationLog);
    }

}
