package top.baihu.platform.system.development.broadcast;

import lombok.Getter;
import lombok.Setter;
import top.baihu.platform.commons.message.broadcast.BroadcastEvent;

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
