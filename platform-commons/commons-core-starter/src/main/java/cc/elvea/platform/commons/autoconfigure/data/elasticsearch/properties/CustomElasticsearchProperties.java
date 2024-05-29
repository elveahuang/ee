package cc.elvea.platform.commons.autoconfigure.data.elasticsearch.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(CustomElasticsearchProperties.PREFIX)
public class CustomElasticsearchProperties {

    public static final String PREFIX = "platform.data.elasticsearch";

    private boolean enabled = false;

}
