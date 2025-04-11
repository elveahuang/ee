package cc.elvea.platform.system.log.api;

import cc.elvea.platform.commons.core.log.domain.ApplicationLogDto;
import cc.elvea.platform.commons.core.log.domain.OperationLogDto;
import cc.elvea.platform.commons.core.log.domain.UrlLogDto;
import cc.elvea.platform.commons.extensions.captcha.domain.CaptchaLogDto;

/**
 * @author elvea
 */
public interface LogApi {

    void saveApplicationLog(ApplicationLogDto dto) throws Exception;

    void saveUrlLogLog(UrlLogDto dto) throws Exception;

    void saveOperationLog(OperationLogDto dto) throws Exception;

    void saveCaptchaLog(CaptchaLogDto dto) throws Exception;

}
