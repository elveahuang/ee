package cc.wdev.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum BizTypeEnum implements BaseEnum<String> {
    TENANT("tenant", "租户业务类型"),
    MEMBER("member", "会员业务类型");

    private final String code;
    private final String description;

    BizTypeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_biz_type__" + this.code.toLowerCase();
    }

}
