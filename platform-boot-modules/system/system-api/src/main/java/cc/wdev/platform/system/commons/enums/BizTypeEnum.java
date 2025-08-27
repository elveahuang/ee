package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 业务类型
 *
 * @author elvea
 */
@Getter
public enum BizTypeEnum implements BaseEnum<Integer> {
    GLOBAL(1, "GLOBAL", "全局"),
    PLATFORM(2, "PLATFORM", "平台"),
    SYSTEM(3, "SYSTEM", "系统"),
    TENANT(4, "TENANT", "租户"),
    IDENTITY(5, "IDENTITY", "全局实体"),
    USER(6, "TENANT", "租户"),
    ACCOUNT(7, "ACCOUNT", "账号");

    private final Integer value;
    private final String code;
    private final String description;

    BizTypeEnum(final Integer value, final String code, final String description) {
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
        return "label_vip_biz_type__" + this.code.toLowerCase();
    }

}
