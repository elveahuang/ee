package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典关联业务类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum DictRelationBizTypeEnum implements BaseBizTypeEnum<String> {
    BANNER("BANNER", "BANNER"),
    LINK("LINK", "LINK"),
    NONE("NONE", "NONE");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.DICT_RELATION_TYPE.getValue().toUpperCase();
    }

}
