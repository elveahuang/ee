package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.constants.SystemCacheConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缓存类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum CacheTypeEnum implements BaseBizTypeEnum<String> {

    USER(SystemCacheConstants.USER, "用户缓存");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return "CacheTypeEnum";
    }

}
