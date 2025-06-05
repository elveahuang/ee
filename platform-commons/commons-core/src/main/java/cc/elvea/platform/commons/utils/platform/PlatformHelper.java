package cc.elvea.platform.commons.utils.platform;

import cc.elvea.platform.commons.utils.ServletUtils;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author elvea
 */
public abstract class PlatformHelper {

    public static Platform fromServletRequest() {
        return fromServletRequest(ServletUtils.getRequest());
    }

    public static Platform fromServletRequest(HttpServletRequest request) {
        return fromUserAgent(ServletUtils.getUserAgent(request));
    }

    public static Platform fromUserAgent(String ua) {
        return Platform.builder().ua(ua).uaObject(UserAgentUtil.parse(ua)).build();
    }

}
