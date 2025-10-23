package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum SeleniumDriverTypeEnum implements BaseBizTypeEnum<String> {
    CHROME("CHROME", "Google Chrome"),
    EDGE("EDGE", "Microsoft Edge");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.SELENIUM_DRIVER_TYPE.getValue().toUpperCase();
    }

}
