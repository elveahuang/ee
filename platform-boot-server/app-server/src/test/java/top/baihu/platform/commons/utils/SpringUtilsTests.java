package top.baihu.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;
import top.baihu.platform.BaseTests;
import top.baihu.platform.commons.core.Context;
import top.baihu.platform.commons.core.sequence.Sequence;

/**
 * @author elvea
 */
@Slf4j
public class SpringUtilsTests extends BaseTests {

    @Test
    public void test() {
        Object context = SpringUtils.getBean("context");
        Assertions.assertNotNull(context);

        context = SpringUtils.getBean("context", Context.class);
        Assertions.assertNotNull(context);

        Assertions.assertTrue(SpringUtils.containsBean("context"));
        Assertions.assertTrue(SpringUtils.isSingleton("context"));
        Assertions.assertTrue(ClassUtils.isAssignable(SpringUtils.getType("context"), Context.class));

        Sequence sequence = SpringUtils.getBean(Sequence.class);
        Assertions.assertNotNull(sequence);
    }

    @Test
    public void txTest() {
    }

}
