package cc.elvea.platform.commons.autoconfigure.message.properties;

import cc.elvea.platform.commons.message.broadcast.BroadcastType;
import cc.elvea.platform.commons.message.broadcast.rabbit.RabbitBroadcastConfig;
import cc.elvea.platform.commons.message.broadcast.redisson.RedissonBroadcastConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = BroadcastProperties.PREFIX)
public class BroadcastProperties implements Serializable {

    public static final String PREFIX = "platform.message.broadcast";

    public static final String PREFIX_RABBIT = "platform.message.broadcast.rabbit";

    public static final String PREFIX_REDISSON = "platform.message.broadcast.redisson";

    private Boolean enabled = Boolean.TRUE;

    private BroadcastType type = BroadcastType.Redisson;

    @NestedConfigurationProperty
    private RedissonBroadcastConfig redisson = new RedissonBroadcastConfig();

    @NestedConfigurationProperty
    private RabbitBroadcastConfig rabbit = new RabbitBroadcastConfig();

}
