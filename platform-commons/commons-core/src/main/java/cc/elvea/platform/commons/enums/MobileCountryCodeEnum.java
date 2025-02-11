package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * 国家地区区位码枚举
 *
 * @author elvea
 */
@Getter
public enum MobileCountryCodeEnum implements BaseEnum<String> {
    ZH_CN("0086", "中国大陆"),
    ZH_HK("00852", "中国香港"),
    ZH_TW("00886", "中国台湾"),
    ZH_MO("00853", "中国澳门");

    private final String code;
    private final String description;

    MobileCountryCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return "label_mobile_country_code__".concat(this.code.toLowerCase());
    }

}
