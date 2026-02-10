package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 地址实体关联业务类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum AddressRelationBizTypeEnum implements BaseBizTypeEnum<String> {
    NONE("NONE", "未指定");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ADDRESS_RELATION_TYPE.getValue().toUpperCase();
    }

}
