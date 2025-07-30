package cc.elvea.platform.commons.core.sequence;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * IdWorkerTests
 *
 * @author elvea
 */
public class SequenceTests extends BaseTests {

    @Autowired
    private Sequence sequence;

    @Test
    public void test() {
        Assertions.assertNotNull(this.sequence);
        Assertions.assertTrue(this.sequence.nextId() > 0);
    }

    @Test
    public void generateCodeTest() {
        String code = this.sequence.generateCode();
        Assertions.assertTrue(StringUtils.isNotBlank(code));
    }

}
