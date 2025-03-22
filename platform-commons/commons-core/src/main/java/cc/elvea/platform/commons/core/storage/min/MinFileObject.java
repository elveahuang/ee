package cc.elvea.platform.commons.core.storage.min;

import cc.elvea.platform.commons.core.storage.model.AbstractFileObject;
import cc.elvea.platform.commons.core.storage.model.FileObject;
import cc.elvea.platform.commons.enums.StorageTypeEnum;
import io.minio.GenericResponse;
import lombok.*;

import java.io.File;

/**
 * @author elvea
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
