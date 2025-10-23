package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum EntityOpenIdBizTypeEnum implements BaseBizTypeEnum<String> {
    NONE("NONE", "未指定");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ENTITY_OPEN_ID_TYPE.getValue().toUpperCase();
    }

}
