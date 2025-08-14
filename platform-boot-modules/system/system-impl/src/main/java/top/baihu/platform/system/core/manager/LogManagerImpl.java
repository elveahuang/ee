package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.core.log.domain.ApplicationLogDto;
import top.baihu.platform.commons.core.log.domain.OperationLogDto;
import top.baihu.platform.commons.core.log.domain.UrlLogDto;
import top.baihu.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import top.baihu.platform.system.core.service.CaptchaLogAmqpService;
import top.baihu.platform.system.core.service.OperationLogAmqpService;
import top.baihu.platform.system.core.service.UrlLogAmqpService;

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
