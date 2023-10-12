package cn.elvea.platform.system.log.api;

import cn.elvea.platform.commons.core.extensions.captcha.domain.CaptchaLogDto;
import cn.elvea.platform.commons.core.log.dto.OperationLogDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface LogApi {

    void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception;

    void saveOperationLog(OperationLogDto operationLog) throws Exception;

}
