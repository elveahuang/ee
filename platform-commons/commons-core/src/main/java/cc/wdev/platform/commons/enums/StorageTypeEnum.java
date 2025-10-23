package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum StorageTypeEnum implements BaseBizTypeEnum<String> {
    AWS("AWS", "亚马逊对象存储，支持S3协议，大部分对象存储都兼容"),
    OSS("OSS", "阿里云对象存储"),
    COS("COS", "腾讯云对象存储");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.STORAGE_TYPE.getValue().toUpperCase();
    }

}
