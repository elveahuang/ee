package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum ConfigTypeEnum implements BaseBizTypeEnum<String> {
    AUTO("AUTO", "自动配置"),
    MANUAL("MANUAL", "手动配置");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.CONFIG_TYPE.getValue().toUpperCase();
    }

}
