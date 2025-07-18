package cc.elvea.platform.commons.autoconfigure.core.properties;

import cc.elvea.platform.commons.core.ai.AiProvider;
import cc.elvea.platform.commons.core.ai.vs.VectorStoreProvider;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AiProperties.PREFIX)
public class AiProperties {

    public static final String PREFIX = "platform.ai";

    private boolean enabled = false;

    private AiProvider provider = AiProvider.SPRING;

    @NestedConfigurationProperty
    private AiProperties.VectorStore vectorStore = AiProperties.VectorStore.builder().build();

    @Data
    @Builder
    public static class VectorStore {
        @Builder.Default
        private boolean enabled = false;

        @Builder.Default
        private VectorStoreProvider provider = VectorStoreProvider.SIMPLE;
    }

}
