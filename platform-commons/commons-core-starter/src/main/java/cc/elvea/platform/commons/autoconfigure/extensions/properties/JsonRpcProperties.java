package cc.elvea.platform.commons.autoconfigure.extensions.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@ConfigurationProperties(JsonRpcProperties.PREFIX)
public class JsonRpcProperties {
    public static final String PREFIX = "platform.jsonrpc";

    private boolean enabled = true;
}
