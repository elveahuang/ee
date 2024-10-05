package cc.elvea.platform.commons.core.storage.oss;

import cc.elvea.platform.commons.core.storage.domain.AbstractFileObject;
import cc.elvea.platform.commons.core.storage.domain.FileObject;
import cc.elvea.platform.commons.enums.StorageTypeEnum;
import com.aliyun.oss.model.GenericResult;
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
public class OssFileObject extends AbstractFileObject<GenericResult> implements FileObject<GenericResult> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.OSS;

    private String key;

    private File object;

    private String url;

    private GenericResult response;

}
