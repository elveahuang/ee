package cn.elvea.platform.commons.core.autoconfigure.oapis.lark.properties;

import com.lark.oapi.core.enums.AppType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = LarkProperties.PREFIX)
public class LarkProperties {

    public static final String PREFIX = "platform.oapis.lark";

    private Boolean enabled = Boolean.FALSE;

    private String cacheKeyPrefix = "lark";

    // =================================================================================================================
    // 默认应用配置
    // =================================================================================================================

    private AppType appType = AppType.SELF_BUILT;

    private String appId;

    private String appSecret;

    private String verificationToken;

    private String encryptKey;

    private Boolean debug = Boolean.FALSE;

}
