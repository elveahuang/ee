package cc.elvea.platform.commons.autoconfigure.oapis.dingtalk.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = DingTalkProperties.PREFIX)
public class DingTalkProperties {

    public static final String PREFIX = "platform.oapis.dingtalk";

    private boolean enabled = false;

    private String cacheKeyPrefix = "dingtalk";

    // =================================================================================================================
    // 默认应用配置
    // =================================================================================================================

    private String corpId;

    private String agentId;

    private String appKey;

    private String appSecret;

}
