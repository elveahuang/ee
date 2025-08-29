package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 权限业务类型
 * 租户权限 - 针对租户设置的系统功能集合
 * 会员权限 - 针对账号设置的系统功能集合
 *
 * @author elvea
 */
@Getter
public enum AuthorityBizEnum implements BaseEnum<Integer> {
    TENANT(1, "TENANT", "租户套餐"),
    MEMBER(2, "MEMBER", "会员套餐");

    private final Integer value;
    private final String code;
    private final String description;

    AuthorityBizEnum(final Integer value, final String code, final String description) {
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
        return "label_authority_biz_type__" + this.code.toLowerCase();
    }

}
