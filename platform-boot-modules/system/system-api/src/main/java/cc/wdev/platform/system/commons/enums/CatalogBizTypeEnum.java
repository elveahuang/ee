package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 目录类型
 * SYSTEM - 系统目录
 * CUSTOM - 自定义目录
 * TEMPLATE - 模板目录
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum CatalogBizTypeEnum implements BaseBizTypeEnum<String> {
    SYSTEM("SYSTEM", "系统目录"),
    CUSTOM("CUSTOM", "自定义目录"),
    TEMPLATE("TEMPLATE", "模板目录");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.CATALOG_TYPE.getValue().toUpperCase();
    }

}
