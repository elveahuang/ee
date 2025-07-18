package cc.elvea.platform.commons.autoconfigure.core.properties;

import cc.elvea.platform.commons.core.ai.enums.AiServiceProvider;
import cc.elvea.platform.commons.core.ai.enums.AiVectorStoreProvider;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AiProperties.PREFIX)
public class AiProperties {

    public static final String PREFIX = "platform.ai";

    private boolean enabled = false;

    private AiServiceProvider serviceProvider = AiServiceProvider.SPRING;

    private AiVectorStoreProvider vectorStoreProvider = AiVectorStoreProvider.SIMPLE;

}
