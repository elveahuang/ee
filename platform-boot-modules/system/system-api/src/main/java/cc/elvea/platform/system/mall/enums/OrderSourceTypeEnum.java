package cc.elvea.platform.system.mall.enums;

import cc.elvea.platform.commons.enums.BaseEnum;
import lombok.Getter;

/**
 * 订单来源
 *
 * @author elvea
 */
@Getter
public enum OrderSourceTypeEnum implements BaseEnum<Integer> {
    APP(1, "APP", "APP"),
    WEB(2, "WEB", "WEB");

    private final Integer value;
    private final String code;
    private final String description;

    OrderSourceTypeEnum(final Integer value, final String code, final String description) {
        this.value = value;
        this.code = code;
        this.description = description;
    }

    @Override
    public String getLabel() {
        return ("label_source_type__".concat(this.code)).toLowerCase();
    }

}
