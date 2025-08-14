package top.baihu.platform.commons.core.sequence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.utils.StringUtils;

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
