package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 国家地区区位码枚举
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum MobileCountryCodeTypeEnum implements BaseBizTypeEnum<String> {
    ZH_CN("0086", "中国大陆"),
    ZH_HK("00852", "中国香港"),
    ZH_TW("00886", "中国台湾"),
    ZH_MO("00853", "中国澳门");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.MOBILE_COUNTRY_CODE_TYPE.getValue().toUpperCase();
    }

}
