package cn.elvea.platform.commons.core.autoconfigure.storage.properties;

import cn.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.core.storage.enums.StorageType;
import cn.elvea.platform.commons.core.storage.local.LocalStorageConfig;
import cn.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "platform.storage")
public class StorageProperties {

    private Boolean enabled = Boolean.FALSE;

    private StorageType type = StorageType.LOCAL;

    @NestedConfigurationProperty
    private LocalStorageConfig local = new LocalStorageConfig();

    @NestedConfigurationProperty
    private CosStorageConfig cos = new CosStorageConfig();

    @NestedConfigurationProperty
    private OssStorageConfig oss = new OssStorageConfig();

    @NestedConfigurationProperty
    private MinStorageConfig min = new MinStorageConfig();

}
