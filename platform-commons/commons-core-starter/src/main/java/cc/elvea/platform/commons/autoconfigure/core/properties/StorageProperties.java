package cc.elvea.platform.commons.autoconfigure.core.properties;

import cc.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cc.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cc.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import cc.elvea.platform.commons.enums.StorageTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = StorageProperties.PREFIX)
public class StorageProperties {

    public static final String PREFIX = "platform.storage";

    private boolean enabled = false;

    private StorageTypeEnum type = StorageTypeEnum.MIN;

    @NestedConfigurationProperty
    private CosStorageConfig cos = new CosStorageConfig();

    @NestedConfigurationProperty
    private OssStorageConfig oss = new OssStorageConfig();

    @NestedConfigurationProperty
    private MinStorageConfig min = new MinStorageConfig();

}
