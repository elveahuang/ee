package cc.elvea.platform.system.development.broadcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Component
public class TestBroadcastHandler implements ApplicationListener<TestBroadcastEvent> {

    @Override
    public void onApplicationEvent(TestBroadcastEvent event) {
        log.info(event.getData());
    }

}
