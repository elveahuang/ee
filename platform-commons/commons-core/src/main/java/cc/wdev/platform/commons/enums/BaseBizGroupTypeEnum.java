package cc.wdev.platform.commons.enums;

/**
 * 业务分组类型枚举
 *
 * @author elvea
 */
public interface BaseBizGroupTypeEnum extends BaseEnum<String> {

    default String getCode() {
        return String.valueOf(getValue());
    }

}
