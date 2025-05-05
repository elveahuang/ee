package cc.elvea.platform.commons.message.broadcast.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author elvea
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RabbitBroadcastConfig implements Serializable {

    private Boolean enabled = Boolean.FALSE;

    private String exchange = "broadcastExchange";

    private String queue = "broadcastQueue";

}
