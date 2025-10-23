package cc.wdev.platform.commons.enums;

import cc.wdev.platform.commons.utils.ObjectUtils;

import java.io.Serializable;
import java.util.EnumSet;

/**
 * 业务类型枚举
 *
 * @author elvea
 */
public interface BaseBizTypeEnum<T extends Serializable> extends BaseEnum<T> {

    String getGroup();

    default String getCode() {
        return String.valueOf(getValue());
    }

    default String getLabel() {
        return ("label__" + getGroup() + "__" + getValue()).toLowerCase();
    }

    default String getKey() {
        return (getGroup() + "_" + getValue()).toUpperCase();
    }

    static <E extends Enum<E> & BaseBizTypeEnum<?>> E getEnumByCode(Object code, Class<E> clazz) {
        return getEnumByCode(code, clazz, null);
    }

    static <E extends Enum<E> & BaseBizTypeEnum<?>> E getEnumByCode(Object code, Class<E> clazz, E defaultValue) {
        EnumSet<E> enumSet = EnumSet.allOf(clazz);
        return enumSet.stream()
            .filter(e -> ObjectUtils.equals(e.getCode(), code))
            .findFirst()
            .orElse(defaultValue);
    }

}
