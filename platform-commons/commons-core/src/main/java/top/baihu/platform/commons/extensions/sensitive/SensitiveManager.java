package top.baihu.platform.commons.extensions.sensitive;

import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.utils.ObjectUtils;
import top.baihu.platform.commons.utils.SpringUtils;

/**
 * @author elvea
 */
@Slf4j
public class SensitiveManager {

    private static final SensitiveService service = new DefaultSensitiveService();

    public static SensitiveService getService() {
        return ObjectUtils.nvl(SpringUtils.getBean(SensitiveService.class), service);
    }

}
