package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标签关联业务类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum TagRelationBizTypeEnum implements BaseBizTypeEnum<String> {
    NONE("NONE", "NONE");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.TAG_RELATION_TYPE.getValue().toUpperCase();
    }

}
