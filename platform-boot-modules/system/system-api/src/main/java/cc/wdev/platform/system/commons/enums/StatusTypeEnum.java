package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 发布状态类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum StatusTypeEnum implements BaseBizTypeEnum<Integer> {
    ON(1, "ON", "发布"),
    OFF(0, "OFF", "未发布");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.STATUS_TYPE.getValue();
    }

}
