package cn.elvea.platform.commons.core.extensions.platform;

import cn.elvea.platform.commons.core.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

/**
 * 基于yauaa获取当前客户端环境信息
 * <a href="https://yauaa.basjes.nl/">...</a>
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class PlatformHelper {

    public final static UserAgentAnalyzer uaa = UserAgentAnalyzer
            .newBuilder()
            .hideMatcherLoadStats()
            .withCache(10000)
            .build();

    public static Platform fromServletRequest(HttpServletRequest request) {
        return fromUserAgent(ServletUtils.getUserAgent());
    }

    public static Platform fromUserAgent(String agent) {
        UserAgent ua = uaa.parse(agent);
        for (String fieldName : ua.getAvailableFieldNamesSorted()) {
            System.out.println(fieldName + " = " + ua.getValue(fieldName));
        }
        return Platform.builder().ua(ua).build();
    }

}
