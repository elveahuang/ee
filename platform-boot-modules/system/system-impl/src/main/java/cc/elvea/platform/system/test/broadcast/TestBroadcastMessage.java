package cc.elvea.platform.system.test.broadcast;

import cc.elvea.platform.commons.message.broadcast.BroadcastMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
