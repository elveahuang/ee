package cn.elvea.platform.commons.core.utils;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author elvea
 * @since 0.0.1
 */
public class CollectionUtilsTests {

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("A", "A");
        Assertions.assertEquals(2, list.size());
        List<String> newList = list.stream().distinct().toList();
        Assertions.assertEquals(1, newList.size());
    }

}
