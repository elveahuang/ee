package top.baihu.platform.commons.autoconfigure.core.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import top.baihu.platform.commons.core.ai.enums.AiServiceProvider;
import top.baihu.platform.commons.core.ai.enums.AiVectorStoreProvider;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = AiProperties.PREFIX)
public class AiProperties {

    public static final String PREFIX = "platform.ai";

    private boolean enabled = false;

    private AiServiceProvider serviceProvider = AiServiceProvider.SPRING;

    private AiVectorStoreProvider vectorStoreProvider = AiVectorStoreProvider.SIMPLE;

}
