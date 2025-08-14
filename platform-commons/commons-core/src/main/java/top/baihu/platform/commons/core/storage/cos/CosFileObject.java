package top.baihu.platform.commons.core.storage.cos;

import com.qcloud.cos.model.COSObject;
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
public class CosFileObject extends AbstractFileObject<COSObject> implements FileObject<COSObject> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.COS;

    private String key;

    private String url;

    private File object;

    private COSObject response;

}
