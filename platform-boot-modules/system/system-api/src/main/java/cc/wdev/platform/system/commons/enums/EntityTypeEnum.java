package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 主体类型
 * 1. USER      - 用户体系，适用于后台系统
 * 2. ACCOUNT   - 账号体系，适用于前台系统
 *
 * @author elvea
 */
@Getter
public enum EntityTypeEnum implements BaseEnum<Integer> {
    TENANT(0, "TENANT", "组户"),
    USER(1, "USER", "用户"),
    ACCOUNT(2, "ACCOUNT", "账号");

    private final Integer value;
    private final String code;
    private final String description;

    EntityTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_subject_type__".concat(this.code)).toLowerCase();
    }

}
