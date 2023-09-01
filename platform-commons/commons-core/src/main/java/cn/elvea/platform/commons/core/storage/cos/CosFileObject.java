package cn.elvea.platform.commons.core.storage.cos;

import cn.elvea.platform.commons.core.storage.domain.AbstractFileObject;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.enums.StorageType;
import com.qcloud.cos.model.COSObject;
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
public class CosFileObject extends AbstractFileObject<COSObject> implements FileObject<COSObject> {

    @Builder.Default
    private StorageType storageType = StorageType.COS;

    private String key;

    private File object;

    private COSObject response;

}
