package cc.elvea.platform.system.log.api;

import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface LogApi {

    void saveUrlLogLog(UrlLogDto captchaLog) throws Exception;

    void saveCaptchaLog(CaptchaLogDto captchaLog) throws Exception;

    void saveOperationLog(OperationLogDto operationLog) throws Exception;

}
