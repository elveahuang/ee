package cc.elvea.platform.commons.core.storage;

import cc.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cc.elvea.platform.commons.core.storage.cos.CosStorageService;
import cc.elvea.platform.commons.core.storage.cos.CosStorageServiceImpl;
import cc.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cc.elvea.platform.commons.core.storage.min.MinStorageService;
import cc.elvea.platform.commons.core.storage.min.MinStorageServiceImpl;
import cc.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import cc.elvea.platform.commons.core.storage.oss.OssStorageService;
import cc.elvea.platform.commons.core.storage.oss.OssStorageServiceImpl;

/**
 * @author elvea
 * @since 24.1.0
 */
public class Storage {

    private final StorageConfig config;

    public Storage(StorageConfig config) {
        this.config = config;
    }

    public StorageService getStorageService() {
        return switch (this.config.getType()) {
            case COS -> getCosStorageService();
            case OSS -> getOssStorageService();
            default -> getMinStorageService();
        };
    }

    public MinStorageService getMinStorageService() {
        return this.getMinStorageService(this.config.getMin());
    }

    public MinStorageService getMinStorageService(MinStorageConfig config) {
        return new MinStorageServiceImpl(config);
    }

    public OssStorageService getOssStorageService() {
        return this.getOssStorageService(this.config.getOss());
    }

    public OssStorageService getOssStorageService(OssStorageConfig config) {
        return new OssStorageServiceImpl(config);
    }

    public CosStorageService getCosStorageService() {
        return this.getCosStorageService(this.config.getCos());
    }

    public CosStorageService getCosStorageService(CosStorageConfig config) {
        return new CosStorageServiceImpl(config);
    }

}
