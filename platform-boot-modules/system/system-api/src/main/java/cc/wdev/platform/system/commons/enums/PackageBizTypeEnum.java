package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 套餐类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum PackageBizTypeEnum implements BaseBizTypeEnum<String> {
    TENANT("TENANT", "租户套餐"),
    MEMBER("MEMBER", "会员套餐");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.PACKAGE_TYPE.getValue().toUpperCase();
    }

}
