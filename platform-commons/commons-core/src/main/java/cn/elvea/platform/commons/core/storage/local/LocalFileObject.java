package cn.elvea.platform.commons.core.storage.local;

import cn.elvea.platform.commons.core.storage.domain.AbstractFileObject;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.enums.StorageType;
import lombok.*;

import java.io.File;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocalFileObject extends AbstractFileObject<File> implements FileObject<File> {

    @Builder.Default
    private StorageType storageType = StorageType.LOCAL;

    private String key;

    private File object;

    private File response;

}
