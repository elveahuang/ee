package cc.elvea.platform.system.development.broadcast;

import cc.elvea.platform.commons.message.broadcast.BroadcastEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elvea
 * @since 24.1.0
 */
@Getter
@Setter
public class TestBroadcastEvent extends BroadcastEvent<String> {

    public TestBroadcastEvent(Object source, String data) {
        super(source, data);
    }

}
