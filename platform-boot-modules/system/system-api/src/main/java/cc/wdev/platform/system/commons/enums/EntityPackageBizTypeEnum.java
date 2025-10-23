package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum EntityPackageBizTypeEnum implements BaseBizTypeEnum<String> {
    TENANT("TENANT", "租户-套餐"),
    NONE("NONE", "未指定");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ENTITY_PACKAGE_TYPE.getValue().toUpperCase();
    }

}
