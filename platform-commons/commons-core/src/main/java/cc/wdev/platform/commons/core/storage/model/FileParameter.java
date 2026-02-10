package cc.wdev.platform.commons.core.storage.model;

import cc.wdev.platform.commons.enums.StorageAccessTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件参数
 *
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileParameter implements Serializable {

    /**
     * 媒体类型
     */
    private String contentType;

    /**
     * 原始文件名
     */
    private String originalFilename;

    /**
     * 目标文件名
     */
    private String filename;

    /**
     * 文件大小
     */
    private long size;

    /**
     * 存储路径
     */
    private String path;

    /**
     * 存储标识
     */
    private String key;

    /**
     * 访问类型
     */
    private StorageAccessTypeEnum accessType;

    public static FileParameter withPublic() {
        return FileParameter.builder().accessType(StorageAccessTypeEnum.PUBLIC).build();
    }

    public static FileParameter withPrivate() {
        return FileParameter.builder().accessType(StorageAccessTypeEnum.PRIVATE).build();
    }

    public static FileParameter withDefault() {
        return FileParameter.withPublic();
    }

}
