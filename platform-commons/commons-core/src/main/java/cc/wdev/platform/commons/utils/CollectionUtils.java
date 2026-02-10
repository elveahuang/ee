package cc.wdev.platform.commons.utils;

import com.google.common.collect.Lists;
import org.jspecify.annotations.Nullable;

import java.util.*;

/**
 * @author elvea
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static <E> Collection<E> nvl(@Nullable Collection<E> collection) {
        return isNotEmpty(collection) ? collection : Collections.emptyList();
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static <E> List<E> toArrayList(final Iterable<E> iterable) {
        return Lists.newArrayList(iterable);
    }

    public static <E> List<E> toArrayList(final Iterator<E> iterator) {
        return Lists.newArrayList(iterator);
    }

}
