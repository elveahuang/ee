package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum LabelTypeEnum implements BaseBizTypeEnum<String> {
    PROPERTIES("properties", "messages_%s", "properties"),
    JSON("json", "%s", "json");

    private final String value;
    private final String filename;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.LABEL_TYPE.getValue();
    }

}
