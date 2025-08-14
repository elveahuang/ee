package top.baihu.platform.commons.utils.time;

import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.utils.ObjectUtils;
import top.baihu.platform.commons.utils.SpringUtils;

/**
 * @author elvea
 */
@Slf4j
public class TimeZoneManager {

    private static final TimeZoneResolver resolver = new DefaultTimeZoneResolver();

    public static TimeZoneResolver getResolver() {
        return ObjectUtils.nvl(SpringUtils.getBean(TimeZoneResolver.class), resolver);
    }

}
