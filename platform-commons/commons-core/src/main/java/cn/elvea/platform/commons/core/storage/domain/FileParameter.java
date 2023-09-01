package cn.elvea.platform.commons.core.storage.domain;

import cn.elvea.platform.commons.core.storage.enums.FileAccessTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件参数
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileParameter implements Serializable {

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
