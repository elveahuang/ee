package cn.elvea.platform.system.commons.enums;

import cn.elvea.platform.commons.core.enums.BaseEnum;
import cn.elvea.platform.system.commons.constants.SystemCacheConstants;

/**
 * 缓存类型
 *
 * @author elvea
 * @since 0.0.1
 */
public enum CacheTypeEnum implements BaseEnum<String> {

    USER(SystemCacheConstants.USER, "用户缓存");

    private final String key;

    private final String description;

    CacheTypeEnum(final String key, final String description) {
        this.key = key;
        this.description = description;
    }

    @Override
    public String getValue() {
        return this.key;
    }

    @Override
    public String getLabel() {
        return "label_cache_type__" + this.key.toLowerCase();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

}
