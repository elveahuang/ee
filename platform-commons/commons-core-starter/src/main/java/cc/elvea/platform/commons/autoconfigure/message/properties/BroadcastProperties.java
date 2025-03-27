package cc.elvea.platform.commons.autoconfigure.message.properties;

import cc.elvea.platform.commons.message.broadcast.BroadcastType;
import cc.elvea.platform.commons.message.broadcast.rabbit.RabbitBroadcastConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = BroadcastProperties.PREFIX)
public class BroadcastProperties implements Serializable {

    public static final String PREFIX = "platform.message.broadcast";

    public static final String PREFIX_RABBIT = "platform.message.broadcast.rabbit";

    private boolean enabled = false;

    private BroadcastType type = BroadcastType.Rabbit;

    @NestedConfigurationProperty
    private RabbitBroadcastConfig rabbit = new RabbitBroadcastConfig();

}
