package cc.elvea.platform.commons.core.storage.oss;

import cc.elvea.platform.commons.core.storage.StorageService;
import com.aliyun.oss.OSS;

/**
 * @author elvea
 */
public interface OssStorageService extends StorageService {

    /**
     * 获取客户端
     */
    OSS getClient();

    /**
     * 关闭客户端
     */
    void closeClient(OSS client);

    /**
     * 获取存储桶名称
     */
    String getBucketName();

    /**
     * 自定义域名
     */
    String getDomain();

}
