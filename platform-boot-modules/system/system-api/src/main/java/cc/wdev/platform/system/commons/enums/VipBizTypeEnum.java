package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum VipBizTypeEnum implements BaseEnum<String> {
    TENANT("tenant", "租户业务类型"),
    ACCOUNT("account", "会员业务类型");

    private final String code;
    private final String description;

    VipBizTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_vip_biz_type__" + this.code.toLowerCase();
    }

}
