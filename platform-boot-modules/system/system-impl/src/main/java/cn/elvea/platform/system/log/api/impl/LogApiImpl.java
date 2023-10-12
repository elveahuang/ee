package cn.elvea.platform.system.log.api.impl;

import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.core.log.dto.OperationLogDto;
import cn.elvea.platform.system.log.api.LogApi;
import cn.elvea.platform.system.log.service.CaptchaLogAmqpService;
import cn.elvea.platform.system.log.service.OperationLogAmqpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Service
@AllArgsConstructor
public class LogApiImpl implements LogApi {

    private final CaptchaLogAmqpService captchaLogAmqpService;

    private final OperationLogAmqpService operationLogAmqpService;

    @Override
    public void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception {
        this.captchaLogAmqpService.send(captchaLog);
    }

    @Override
    public void saveOperationLog(OperationLogDto operationLog) throws Exception {
        this.operationLogAmqpService.send(operationLog);
    }

}
