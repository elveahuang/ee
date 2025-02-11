package cc.elvea.platform.system.test.broadcast;

import cc.elvea.platform.commons.message.broadcast.BroadcastEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * @author elvea
 */
@Getter
@Setter
public class TestBroadcastEvent extends BroadcastEvent<String> {

    public TestBroadcastEvent(Object source, String data) {
        super(source, data);
    }

}
