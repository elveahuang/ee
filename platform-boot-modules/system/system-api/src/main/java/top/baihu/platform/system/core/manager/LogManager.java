package top.baihu.platform.system.core.manager;

import top.baihu.platform.commons.core.log.domain.ApplicationLogDto;
import top.baihu.platform.commons.core.log.domain.OperationLogDto;
import top.baihu.platform.commons.core.log.domain.UrlLogDto;
import top.baihu.platform.commons.extensions.captcha.domain.CaptchaLogDto;

/**
 * @author elvea
 */
public interface LogManager {

    void saveApplicationLog(ApplicationLogDto dto) throws Exception;

    void saveUrlLogLog(UrlLogDto dto) throws Exception;

    void saveOperationLog(OperationLogDto dto) throws Exception;

    void saveCaptchaLog(CaptchaLogDto dto) throws Exception;

}
