package cc.wdev.platform.commons.core.storage.aws;

import cc.wdev.platform.commons.core.storage.domain.AbstractFileObject;
import cc.wdev.platform.commons.core.storage.domain.FileObject;
import cc.wdev.platform.commons.enums.StorageTypeEnum;
import lombok.*;
import software.amazon.awssdk.services.s3.model.S3Response;

import java.io.File;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AwsFileObject extends AbstractFileObject<S3Response> implements FileObject<S3Response> {

    @Builder.Default
    private StorageTypeEnum storageType = StorageTypeEnum.AWS;

    private String key;

    private String url;

    private File object;

    private S3Response response;

}
