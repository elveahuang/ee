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
public enum ActiveTypeEnum implements BaseBizTypeEnum<Integer> {
    ENABLED(1, "启用，正常状态"),
    DISABLED(0, "禁用，删除状态");

    private final Integer value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ACTIVE_TYPE.getValue();
    }

}
