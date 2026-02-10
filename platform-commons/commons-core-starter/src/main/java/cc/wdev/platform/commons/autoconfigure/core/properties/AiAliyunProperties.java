package cc.wdev.platform.commons.autoconfigure.core.properties;

import cc.wdev.platform.commons.core.ai.aliyun.AiAliyunConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 */
@Data
@ConfigurationProperties(prefix = AiAliyunProperties.PREFIX)
public class AiAliyunProperties {

    public static final String PREFIX = "platform.ai.aliyun";

    private boolean enabled = true;

    private String apiKey;

    @NestedConfigurationProperty
    private AiAliyunConfig.Transcription transcription = AiAliyunConfig.Transcription.builder().build();

}
