package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 附件类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum AttachmentRelationBizTypeEnum implements BaseEnum<String> {
    BANNER_COVER("BANNER_COVER", "宣传栏电脑端封面"),
    BANNER_MOBILE_COVER("BANNER_MOBILE_COVER", "宣传栏移动端封面"),
    LINK_COVER("LINK_COVER", "友情链接封面"),
    TENANT_COVER("TENANT_COVER", "租户封面"),
    TENANT_PACKAGE_COVER("TENANT_PACKAGE_COVER", "租户套餐封面"),
    NONE("NONE", "NONE");

    private final String value;
    private final String description;
}
