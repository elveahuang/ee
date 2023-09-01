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
@ConfigurationProperties(prefix = WeiXinMaProperties.PREFIX)
public class WeiXinMaProperties {

    public static final String PREFIX = "platform.oapis.weixin.ma";

    private Boolean enabled = Boolean.FALSE;

    private String cacheKeyPrefix = "ma";

    // =================================================================================================================
    // 默认应用配置
    // =================================================================================================================

    private String appId;

    private String appSecret;

}
