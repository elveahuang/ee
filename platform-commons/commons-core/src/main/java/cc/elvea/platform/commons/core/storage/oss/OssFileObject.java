package cc.elvea.platform.commons.core.storage.oss;

import cc.elvea.platform.commons.core.storage.model.AbstractFileObject;
import cc.elvea.platform.commons.core.storage.model.FileObject;
import cc.elvea.platform.commons.enums.StorageTypeEnum;
import com.aliyun.oss.model.GenericResult;
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
public class OssFileObject extends AbstractFileObject<GenericResult> implements FileObject<GenericResult> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.OSS;

    private String key;

    private File object;

    private String url;

    private GenericResult response;

}
