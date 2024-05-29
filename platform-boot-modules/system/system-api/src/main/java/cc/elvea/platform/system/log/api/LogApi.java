package cc.elvea.platform.system.log.api;

import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;
import cc.elvea.platform.commons.logging.domain.OperationLogDto;
import cc.elvea.platform.commons.logging.domain.UrlLogDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface LogApi {

    void saveLogLog(UrlLogDto captchaLog) throws Exception;

    void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception;

    void saveOperationLog(OperationLogDto operationLog) throws Exception;

}
