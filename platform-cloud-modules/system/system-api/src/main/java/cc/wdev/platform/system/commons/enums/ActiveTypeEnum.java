package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ActiveTypeEnum implements BaseBizTypeEnum<Boolean> {
    ACTIVE(Boolean.TRUE, "ACTIVE", "启用"),
    INACTIVE(Boolean.FALSE, "INACTIVE", "禁用");

    private final Boolean value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return "CacheTypeEnum";
    }

}
