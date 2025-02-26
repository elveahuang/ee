package cc.elvea.platform.commons.utils;

import com.google.common.collect.Lists;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author elvea
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

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
