package cc.wdev.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum RegionTypeEnum implements BaseEnum<String> {
    COUNTRY("COUNTRY", "国家、地区"),
    PROVINCE("PROVINCE", "省、自治区、直辖市"),
    CITY("CITY", "地级市、地区、自治州、盟"),
    COUNTY("COUNTY", "市辖区、县级市、县");

    private final String type;

    private final String description;

    RegionTypeEnum(final String type, final String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.type;
    }

    @Override
    public String getLabel() {
        return "label_region_type__".concat(this.type.toLowerCase());
    }

}
