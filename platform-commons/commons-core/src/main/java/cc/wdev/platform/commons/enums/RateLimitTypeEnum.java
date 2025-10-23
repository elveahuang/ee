package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum RateLimitTypeEnum implements BaseBizTypeEnum<String> {
    DEFAULT("DEFAULT", "默认限流"),
    IP("IP", "IP限流"),
    CLUSTER("CLUSTER", "CLUSTER限流");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.MOBILE_COUNTRY_CODE_TYPE.getValue().toUpperCase();
    }

}
