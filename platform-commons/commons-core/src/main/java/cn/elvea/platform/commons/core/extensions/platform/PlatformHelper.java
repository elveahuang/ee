package cn.elvea.platform.commons.core.extensions.platform;

import cn.elvea.platform.commons.core.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import nl.basjes.parse.useragent.UserAgent;
import nl.basjes.parse.useragent.UserAgentAnalyzer;

/**
 * 获取当前客户端环境信息
 * <a href="https://yauaa.basjes.nl/">...</a>
 *
 * @author elvea
 * @since 0.0.1
 */
public class PlatformHelper {

    private final UserAgentAnalyzer uaa = UserAgentAnalyzer
            .newBuilder()
            .hideMatcherLoadStats()
            .withCache(1000)
            .build();

    private PlatformHelper() {
        //
    }

    public static PlatformHelper getInstance() {
        return new PlatformHelper();
    }

    public Platform fromServletRequest(HttpServletRequest request) {
        return fromUserAgent(ServletUtils.getUserAgent());
    }

    public Platform fromUserAgent(String agent) {
        UserAgent ua = uaa.parse(agent);
        for (String fieldName : ua.getAvailableFieldNamesSorted()) {
            System.out.println(fieldName + " = " + ua.getValue(fieldName));
        }
        return Platform.builder().ua(ua).build();
    }

}
