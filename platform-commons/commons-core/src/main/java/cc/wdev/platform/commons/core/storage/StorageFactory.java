package cc.wdev.platform.commons.core.storage;

import cc.wdev.platform.commons.core.storage.aws.AwsStorageConfig;
import cc.wdev.platform.commons.core.storage.aws.AwsStorageService;
import cc.wdev.platform.commons.core.storage.aws.AwsStorageServiceImpl;
import cc.wdev.platform.commons.core.storage.cos.CosStorageConfig;
import cc.wdev.platform.commons.core.storage.cos.CosStorageService;
import cc.wdev.platform.commons.core.storage.cos.CosStorageServiceImpl;
import cc.wdev.platform.commons.core.storage.oss.OssStorageConfig;
import cc.wdev.platform.commons.core.storage.oss.OssStorageService;
import cc.wdev.platform.commons.core.storage.oss.OssStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record StorageFactory(StorageConfig config) {

    public StorageService getStorageService() {
        return switch (this.config.getType()) {
            case COS -> getCosStorageService();
            case OSS -> getOssStorageService();
            default -> getAwsStorageService();
        };
    }

    public StorageConfig getConfig() {
        return this.config;
    }

    public AwsStorageService getAwsStorageService() {
        return this.getAwsStorageService(this.config.getAws());
    }

    public AwsStorageService getAwsStorageService(AwsStorageConfig config) {
        return new AwsStorageServiceImpl(config);
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
