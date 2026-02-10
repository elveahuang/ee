package cc.wdev.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author elvea
 */
@Slf4j
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

    @Test
    public void uuidTests() {
        String uuid = StringUtils.uuid();
        log.info("uuid: {}", uuid);
        Assertions.assertNotNull(uuid);
    }

}
