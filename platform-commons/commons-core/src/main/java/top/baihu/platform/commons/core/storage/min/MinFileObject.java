package top.baihu.platform.commons.core.storage.min;

import io.minio.GenericResponse;
import lombok.*;
import top.baihu.platform.commons.core.storage.domain.AbstractFileObject;
import top.baihu.platform.commons.core.storage.domain.FileObject;
import top.baihu.platform.commons.enums.StorageTypeEnum;

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
