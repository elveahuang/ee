package cc.elvea.platform.commons.core.storage.min;

import cc.elvea.platform.commons.core.storage.StorageService;
import io.minio.MinioClient;

/**
 * @author elvea
 */
public interface MinStorageService extends StorageService {

    /**
     * 获取客户端
     */
    MinioClient getClient();

    /**
     * 关闭客户端
     */
    void closeClient(MinioClient client);

    /**
     * 获取存储桶名称
     */
    String getBucketName();

    /**
     * 自定义域名
     */
    String getDomain();

}
