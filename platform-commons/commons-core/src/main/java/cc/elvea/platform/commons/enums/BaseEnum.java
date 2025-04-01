package cc.elvea.platform.commons.enums;

import cc.elvea.platform.commons.utils.ObjectUtils;

import java.io.Serializable;
import java.util.EnumSet;

/**
 * 基础枚举接口
 *
 * @author elvea
 */
public interface BaseEnum<T extends Serializable> {

    T getValue();

    String getLabel();

    String getDescription();

    /**
     * 根据值获取枚举
     */
    static <E extends Enum<E> & BaseEnum<?>> E getEnumByValue(Object value, Class<E> clazz) {
        EnumSet<E> enumSet = EnumSet.allOf(clazz);
        return enumSet.stream()
                .filter(e -> ObjectUtils.equals(e.getValue(), value))
                .findFirst()
                .orElse(null);
    }

}
