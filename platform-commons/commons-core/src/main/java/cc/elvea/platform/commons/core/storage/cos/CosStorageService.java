package cc.elvea.platform.commons.core.storage.cos;

import cc.elvea.platform.commons.core.storage.StorageService;
import com.qcloud.cos.COSClient;

/**
 * @author elvea
 */
public interface CosStorageService extends StorageService {

    /**
     * 获取客户端
     */
    COSClient getClient();

    /**
     * 关闭客户端
     */
    void closeClient(COSClient client);

    /**
     * 获取存储桶名称
     */
    String getBucketName();

    /**
     * 自定义域名
     */
    String getDomain();

}
