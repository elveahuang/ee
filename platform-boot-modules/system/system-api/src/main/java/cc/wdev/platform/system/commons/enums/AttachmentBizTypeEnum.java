package cc.wdev.platform.system.commons.enums;

import cc.wdev.platform.commons.enums.BaseBizTypeEnum;
import cc.wdev.platform.system.commons.biz.AttachmentTypeConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static cc.wdev.platform.system.commons.constants.SystemAttachmentConstants.DEFAULT_CONFIG;
import static cc.wdev.platform.system.commons.constants.SystemAttachmentConstants.DEFAULT_IMAGE_CONFIG;

/**
 * 附件业务类型
 *
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum AttachmentBizTypeEnum implements BaseBizTypeEnum<String> {
    USER_AVATAR("AVATAR", "用户头像", DEFAULT_IMAGE_CONFIG),
    BANNER_COVER("BANNER_COVER", "宣传栏电脑端封面", DEFAULT_IMAGE_CONFIG),
    BANNER_MOBILE_COVER("BANNER_MOBILE_COVER", "宣传栏移动端封面", DEFAULT_IMAGE_CONFIG),
    NONE("NONE", "未指定", DEFAULT_CONFIG);

    private final String value;
    private final String description;
    private final AttachmentTypeConfig config;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.ATTACHMENT_TYPE.getValue().toUpperCase();
    }

}
