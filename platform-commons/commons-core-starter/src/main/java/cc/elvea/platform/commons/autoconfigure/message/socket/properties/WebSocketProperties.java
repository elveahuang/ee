package cc.elvea.platform.commons.autoconfigure.message.socket.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = WebSocketProperties.PREFIX)
public class WebSocketProperties implements Serializable {

    public static final String PREFIX = "platform.socket";

    private boolean enabled = true;

}
