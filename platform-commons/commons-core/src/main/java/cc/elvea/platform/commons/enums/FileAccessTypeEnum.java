package cc.elvea.platform.commons.enums;

import lombok.Getter;

/**
 * @author elvea
 */
@Getter
public enum FileAccessTypeEnum {
    /**
     * 公开
     */
    PUBLIC("PUBLIC", "公开"),
    /**
     * 私有
     */
    PRIVATE("PRIVATE", "私有");

    /**
     * 类型
     */
    private final String type;
    /**
     * 描述
     */
    private final String desc;

    FileAccessTypeEnum(final String type, final String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static FileAccessTypeEnum getFileAccessType(String type) {
        FileAccessTypeEnum[] ts = FileAccessTypeEnum.values();
        for (FileAccessTypeEnum t : ts) {
            if (t.getType().equalsIgnoreCase(type)) {
                return t;
            }
        }
        return PUBLIC;
    }

}
