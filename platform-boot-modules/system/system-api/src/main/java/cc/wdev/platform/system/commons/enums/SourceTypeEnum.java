package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据来源类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum SourceTypeEnum implements BaseBizTypeEnum<Integer> {
    SYSTEM(1, "系统内置，一般情况下不允许删除"),
    NORMAL(2, "普通，直接在页面添加的记录，一般情况下允许直接删除"),
    IMP(3, "导入，从导入模版导入的记录，一般情况下允许直接删除"),
    SYNC(4, "同步，从其他系统同步的记录，一般情况下允许直接删除");

    private final Integer value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.SOURCE_TYPE.getValue();
    }

}
