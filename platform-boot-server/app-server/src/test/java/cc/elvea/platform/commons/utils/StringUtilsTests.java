package cc.elvea.platform.commons.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author elvea
 */
public class StringUtilsTests {

    @Test
    public void baseTests() {
        Assertions.assertTrue(StringUtils.isBlank(""));
        Assertions.assertTrue(StringUtils.isNotBlank("A"));
        Assertions.assertTrue(StringUtils.isEmpty(""));
        Assertions.assertTrue(StringUtils.isNotEmpty("A"));

        Assertions.assertEquals(StringUtils.toUpperCase("a"), "A");
        Assertions.assertEquals(StringUtils.toLowerCase("A"), "a");
    }

}
