package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 套餐类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum PackageItemBizTypeEnum implements BaseEnum<String> {
    YEARLY("YEARLY", "按年订阅"),
    MONTHLY("MONTHLY", "按月订阅"),
    NONE("NONE", "NONE");

    private final String value;
    private final String description;
}
