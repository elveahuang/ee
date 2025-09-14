package cc.wdev.platform.commons.core.storage.aws;

import cc.wdev.platform.commons.core.storage.StorageService;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * @author elvea
 */
public interface AwsStorageService extends StorageService {

    /**
     * 获取客户端
     */
    S3Client getClient();

    /**
     * 关闭客户端
     */
    void closeClient(S3Client client);

    /**
     * 获取存储桶名称
     */
    String getBucketName();

    /**
     * 自定义域名
     */
    String getDomain();

}
