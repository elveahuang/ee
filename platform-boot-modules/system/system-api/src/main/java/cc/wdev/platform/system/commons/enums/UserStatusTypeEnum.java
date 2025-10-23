package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum UserStatusTypeEnum implements BaseBizTypeEnum<Integer> {
    DELETED(0, "DELETED", "删除状态"),
    OK(1, "OK", "正常状态");

    private final Integer value;
    private final String code;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.USER_STATUS_TYPE.getValue();
    }

}
