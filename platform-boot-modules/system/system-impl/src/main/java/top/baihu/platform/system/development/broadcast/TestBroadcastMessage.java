package top.baihu.platform.system.development.broadcast;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.baihu.platform.commons.message.broadcast.BroadcastMessage;

/**
 * @author elvea
 */
@Getter
@Setter
@NoArgsConstructor
public class TestBroadcastMessage extends BroadcastMessage<String, TestBroadcastEvent> {

    public TestBroadcastMessage(String data) {
        super(data);
    }

    @Override
    public TestBroadcastEvent toEvent() {
        return new TestBroadcastEvent(this, this.getData());
    }

}
