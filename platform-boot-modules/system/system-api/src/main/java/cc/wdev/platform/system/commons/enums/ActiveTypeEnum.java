package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用状态类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ActiveTypeEnum implements BaseBizTypeEnum<Boolean> {
    ENABLED(Boolean.TRUE, "启用，正常状态"),
    DISABLED(Boolean.FALSE, "禁用，删除状态");

    private final Boolean value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ACTIVE_TYPE.getValue();
    }

}
