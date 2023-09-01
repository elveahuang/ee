package cn.elvea.platform.commons.core.sequence;

import cn.elvea.platform.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * IdWorkerTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class SequenceTests extends BaseTests {

    @Autowired
    private Sequence sequence;

    @Test
    public void test() {
        Assertions.assertNotNull(this.sequence);
        Assertions.assertTrue(this.sequence.nextId() > 0);
    }

}
