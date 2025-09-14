package cc.wdev.platform.commons.core.storage.cos;

import cc.wdev.platform.commons.core.storage.model.FileObject;
import cc.wdev.platform.commons.enums.StorageTypeEnum;
import com.qcloud.cos.model.COSObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CosFileObject implements FileObject<COSObject> {

    @Builder.Default
    private StorageTypeEnum type = StorageTypeEnum.COS;

    private String key;

    private String url;

    private File object;

    private COSObject response;

}
