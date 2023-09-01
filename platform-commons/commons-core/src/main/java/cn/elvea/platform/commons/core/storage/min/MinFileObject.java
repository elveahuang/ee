package cn.elvea.platform.commons.core.storage.min;

import cn.elvea.platform.commons.core.storage.domain.AbstractFileObject;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.enums.StorageType;
import io.minio.GenericResponse;
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
public class MinFileObject extends AbstractFileObject<GenericResponse> implements FileObject<GenericResponse> {

    @Builder.Default
    private StorageType storageType = StorageType.MIN;

    private String key;

    private File object;

    private GenericResponse response;

}
