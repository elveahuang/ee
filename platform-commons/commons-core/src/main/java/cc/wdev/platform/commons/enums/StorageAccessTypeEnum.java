package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum StorageAccessTypeEnum implements BaseBizTypeEnum<String> {
    PUBLIC("PUBLIC", "公开"),
    PRIVATE("PRIVATE", "私有");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.STORAGE_ACCESS_TYPE.getValue().toUpperCase();
    }

}
