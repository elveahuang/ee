package cc.wdev.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AiAliyunProperties.PREFIX)
public class AiAliyunProperties {

    public static final String PREFIX = "platform.ai.aliyun";

    private boolean enabled = true;

    private String apiKey;

}
