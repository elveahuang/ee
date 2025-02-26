package cc.elvea.platform.commons.core.storage;

import cc.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cc.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cc.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import cc.elvea.platform.commons.enums.StorageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageConfig implements Serializable {

    @Builder.Default
    private StorageTypeEnum type = StorageTypeEnum.MIN;

    @Builder.Default
    private CosStorageConfig cos = new CosStorageConfig();

    @Builder.Default
    private OssStorageConfig oss = new OssStorageConfig();

    @Builder.Default
    private MinStorageConfig min = new MinStorageConfig();

}
