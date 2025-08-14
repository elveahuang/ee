package top.baihu.platform.commons.core.storage;

import lombok.extern.slf4j.Slf4j;
import top.baihu.platform.commons.core.storage.cos.CosStorageConfig;
import top.baihu.platform.commons.core.storage.cos.CosStorageService;
import top.baihu.platform.commons.core.storage.cos.CosStorageServiceImpl;
import top.baihu.platform.commons.core.storage.min.MinStorageConfig;
import top.baihu.platform.commons.core.storage.min.MinStorageService;
import top.baihu.platform.commons.core.storage.min.MinStorageServiceImpl;
import top.baihu.platform.commons.core.storage.oss.OssStorageConfig;
import top.baihu.platform.commons.core.storage.oss.OssStorageService;
import top.baihu.platform.commons.core.storage.oss.OssStorageServiceImpl;

/**
 * @author elvea
 */
@Slf4j
public record StorageFactory(StorageConfig config) {

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
