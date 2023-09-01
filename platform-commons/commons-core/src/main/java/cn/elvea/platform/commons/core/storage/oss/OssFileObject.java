package cn.elvea.platform.commons.core.storage.oss;

import cn.elvea.platform.commons.core.storage.domain.AbstractFileObject;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.enums.StorageType;
import com.aliyun.oss.model.OSSObject;
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
public class OssFileObject extends AbstractFileObject<OSSObject> implements FileObject<OSSObject> {

    @Builder.Default
    private StorageType storageType = StorageType.OSS;

    private String key;

    private File object;

    private OSSObject response;

}
