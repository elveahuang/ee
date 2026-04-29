package cc.wdev.platform.commons.autoconfigure.ai.properties;

import cc.wdev.platform.commons.ai.enums.ServiceProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AiProperties.PREFIX)
public class AiProperties {

    public static final String PREFIX = "platform.ai";

    private boolean enabled = false;

    @NestedConfigurationProperty
    private Providers providers = new Providers();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Providers {

        @Builder.Default
        private String defaultProvider = ServiceProvider.DEEPSEEK.getValue();

        @Builder.Default
        @NestedConfigurationProperty
        private ProviderConfig deepseek = new ProviderConfig();

        @Builder.Default
        @NestedConfigurationProperty
        private ProviderConfig openai = new ProviderConfig();

        @Builder.Default
        @NestedConfigurationProperty
        private ProviderConfig aliyun = new ProviderConfig();

        @Builder.Default
        @NestedConfigurationProperty
        private ProviderConfig tencent = new ProviderConfig();

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProviderConfig {

        private boolean enabled = false;

        private String apiKey;

        private String baseUrl;

        @NestedConfigurationProperty
        private ProviderModelConfig models = new ProviderModelConfig();

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProviderModelConfig {

        private String chat;

        private String image;

        private String embedding;

        private String transcription;

    }

}
