package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 套餐业务类型
 * 1. TENANT    - 租户体系，多租户模式下给租户做授权
 * 2. ACCOUNT   - 账号体系，个人账号会员套餐
 *
 * @author elvea
 */
@Getter
public enum PackageBizTypeEnum implements BaseEnum<Integer> {
    TENANT(1, "TENANT", "租户业务类型"),
    ACCOUNT(2, "ACCOUNT", "会员业务类型");

    private final Integer value;
    private final String code;
    private final String description;

    PackageBizTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getLabel() {
        return "label_package_biz_type__" + this.code.toLowerCase();
    }

}
