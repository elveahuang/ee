package cc.elvea.platform.commons.message.broadcast;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author elvea
 */
@Getter
@Slf4j
public class BroadcastEvent<E> extends ApplicationEvent {

    private final E data;

    public BroadcastEvent(Object source, E data) {
        super(source);
        this.data = data;
    }

}
