package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.core.log.domain.ApplicationLogDto;
import cc.wdev.platform.commons.core.log.domain.OperationLogDto;
import cc.wdev.platform.commons.core.log.domain.UrlLogDto;
import cc.wdev.platform.commons.extensions.captcha.domain.CaptchaLogDto;

/**
 * @author elvea
 */
public interface LogApi {

    void saveApplicationLog(ApplicationLogDto dto) throws Exception;

    void saveUrlLogLog(UrlLogDto dto) throws Exception;

    void saveOperationLog(OperationLogDto dto) throws Exception;

    void saveCaptchaLog(CaptchaLogDto dto) throws Exception;

}
