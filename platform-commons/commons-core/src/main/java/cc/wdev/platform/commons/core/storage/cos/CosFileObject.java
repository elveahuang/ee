package cc.wdev.platform.commons.core.storage.cos;

import cc.wdev.platform.commons.core.storage.domain.AbstractFileObject;
import cc.wdev.platform.commons.core.storage.domain.FileObject;
import cc.wdev.platform.commons.enums.StorageTypeEnum;
import com.qcloud.cos.model.COSObject;
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
public class CosFileObject extends AbstractFileObject<COSObject> implements FileObject<COSObject> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.COS;

    private String key;

    private String url;

    private File object;

    private COSObject response;

}
