package cc.elvea.platform.commons.message.broadcast.redisson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedissonBroadcastConfig implements Serializable {

    private Boolean enabled = Boolean.FALSE;

    private String topic = "system_broadcast_topic";

}
