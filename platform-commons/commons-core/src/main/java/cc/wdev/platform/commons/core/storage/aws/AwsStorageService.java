package cc.wdev.platform.commons.core.storage.aws;

import cc.wdev.platform.commons.core.storage.StorageService;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

/**
 * @author elvea
 */
public interface AwsStorageService extends StorageService {

    /**
     * 获取客户端
     */
    S3AsyncClient getClient();

    /**
     * 获取S3 Transfer Manager
     */
    S3TransferManager getTransferManager(S3AsyncClient client);

    /**
     * 关闭客户端
     */
    void closeClient(S3AsyncClient client);

    /**
     * 获取存储桶名称
     */
    String getBucketName();

    /**
     * 自定义域名
     */
    String getDomain();

}
