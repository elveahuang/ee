package cn.elvea.platform.commons.core.autoconfigure.oapis.weixin.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = WeiXinCpProperties.PREFIX)
public class WeiXinCpProperties {

    public static final String PREFIX = "platform.oapis.weixin.cp";

    private Boolean enabled = Boolean.FALSE;

    private String cacheKeyPrefix = "cp";

    // =================================================================================================================
    // 默认应用配置
    // =================================================================================================================

    private String corpId;

    private String corpSecret;

    private Integer agentId;

    private String token;

}
