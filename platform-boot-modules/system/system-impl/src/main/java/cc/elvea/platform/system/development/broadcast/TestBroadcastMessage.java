package cc.elvea.platform.system.development.broadcast;

import cc.elvea.platform.commons.message.broadcast.BroadcastMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author elvea
 * @since 24.1.0
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
