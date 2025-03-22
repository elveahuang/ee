package cc.elvea.platform.commons.core.storage.model;

import cc.elvea.platform.commons.enums.FileAccessTypeEnum;
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
    private FileAccessTypeEnum accessType;

    public static FileParameter withPublic() {
        return FileParameter.builder().accessType(FileAccessTypeEnum.PUBLIC).build();
    }

    public static FileParameter withPrivate() {
        return FileParameter.builder().accessType(FileAccessTypeEnum.PRIVATE).build();
    }

    public static FileParameter withDefault() {
        return FileParameter.withPublic();
    }

}
