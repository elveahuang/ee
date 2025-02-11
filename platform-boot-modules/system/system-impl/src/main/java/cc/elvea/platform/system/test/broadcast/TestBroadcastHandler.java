package cc.elvea.platform.system.test.broadcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author elvea
 */
@Slf4j
@Component
public class TestBroadcastHandler implements ApplicationListener<TestBroadcastEvent> {

    @Override
    public void onApplicationEvent(TestBroadcastEvent event) {
        log.info(event.getData());
    }

}
