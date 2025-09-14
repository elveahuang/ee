package cc.wdev.platform.commons.core.storage;

import cc.wdev.platform.commons.core.storage.aws.AwsStorageConfig;
import cc.wdev.platform.commons.core.storage.aws.AwsStorageService;
import cc.wdev.platform.commons.core.storage.cos.CosStorageConfig;
import cc.wdev.platform.commons.core.storage.cos.CosStorageService;
import cc.wdev.platform.commons.core.storage.oss.OssStorageConfig;
import cc.wdev.platform.commons.core.storage.oss.OssStorageService;
import com.aliyun.oss.OSS;
import com.qcloud.cos.COSClient;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * @author elvea
 */
@Slf4j
public record StorageFactory(StorageConfig config) {

    public StorageService<?> getStorageService() {
        return switch (this.config.getType()) {
            case COS -> getCosStorageService();
            case OSS -> getOssStorageService();
            default -> getAwsStorageService();
        };
    }

    public StorageConfig getConfig() {
        return this.config;
    }

    public StorageService<S3Client> getAwsStorageService() {
        return this.getAwsStorageService(this.config.getAws());
    }

    public StorageService<S3Client> getAwsStorageService(AwsStorageConfig config) {
        return new AwsStorageService(config);
    }

    public StorageService<OSS> getOssStorageService() {
        return this.getOssStorageService(this.config.getOss());
    }

    public StorageService<OSS> getOssStorageService(OssStorageConfig config) {
        return new OssStorageService(config);
    }

    public StorageService<COSClient> getCosStorageService() {
        return this.getCosStorageService(this.config.getCos());
    }

    public StorageService<COSClient> getCosStorageService(CosStorageConfig config) {
        return new CosStorageService(config);
    }

}
