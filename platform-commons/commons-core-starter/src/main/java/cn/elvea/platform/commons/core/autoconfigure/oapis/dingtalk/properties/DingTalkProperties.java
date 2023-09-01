package cn.elvea.platform.commons.core.autoconfigure.oapis.dingtalk.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = DingTalkProperties.PREFIX)
public class DingTalkProperties {

    public static final String PREFIX = "platform.oapis.dingtalk";

    private Boolean enabled = Boolean.FALSE;

    private String cacheKeyPrefix = "dingtalk";

    // =================================================================================================================
    // 默认应用配置
    // =================================================================================================================

    private String corpId;

    private String agentId;

    private String appKey;

    private String appSecret;

}
