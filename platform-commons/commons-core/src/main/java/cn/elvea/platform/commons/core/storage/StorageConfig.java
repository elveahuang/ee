package cn.elvea.platform.commons.core.storage;

import cn.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.core.storage.enums.StorageType;
import cn.elvea.platform.commons.core.storage.local.LocalStorageConfig;
import cn.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageConfig implements Serializable {

    @Builder.Default
    private StorageType type = StorageType.MIN;

    @Builder.Default
    private LocalStorageConfig local = new LocalStorageConfig();

    @Builder.Default
    private CosStorageConfig cos = new CosStorageConfig();

    @Builder.Default
    private OssStorageConfig oss = new OssStorageConfig();

    @Builder.Default
    private MinStorageConfig min = new MinStorageConfig();

}
