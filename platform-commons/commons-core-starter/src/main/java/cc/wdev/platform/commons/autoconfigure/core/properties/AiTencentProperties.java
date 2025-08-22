package cc.wdev.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AiTencentProperties.PREFIX)
public class AiTencentProperties {

    public static final String PREFIX = "platform.ai.tencent";

    private boolean enabled = false;

    private String apiKey;

}
