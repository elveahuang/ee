package cn.elvea.platform.commons.core.extensions.platform;

import lombok.Builder;
import nl.basjes.parse.useragent.UserAgent;

/**
 * @author elvea
 * @since 0.0.1
 */
@Builder
public class Platform {

    private UserAgent ua;

    public Platform parse() {
        return this;
    }

    public Boolean isWeChat() {
        return ua.getUserAgentString().contains("WeChat");
    }

    public Boolean isLark() {
        return ua.getUserAgentString().contains("Lark");
    }

}
