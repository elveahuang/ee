package cc.elvea.platform.commons.storage.min;

import cc.elvea.platform.commons.enums.StorageTypeEnum;
import cc.elvea.platform.commons.storage.domain.AbstractFileObject;
import cc.elvea.platform.commons.storage.domain.FileObject;
import io.minio.GenericResponse;
import lombok.*;

import java.io.File;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MinFileObject extends AbstractFileObject<GenericResponse> implements FileObject<GenericResponse> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.MIN;

    private String key;

    private String url;

    private File object;

    private GenericResponse response;

}
