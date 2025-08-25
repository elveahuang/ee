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
public enum EntitySupervisorEnum implements BaseEnum<Integer> {
    USER_SUPERVISOR(1, "USER_SUPERVISOR", "用户直属上级"),
    USER_TUTORS(2, "USER_TUTORS", "用户带教人"),
    DEPARTMENT_SUPERVISOR(3, "DEPARTMENT_SUPERVISOR", "部门主管");

    private final Integer value;
    private final String code;
    private final String description;

    EntitySupervisorEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_subject_type__".concat(this.code)).toLowerCase();
    }

}
