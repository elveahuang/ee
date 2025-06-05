package cc.elvea.platform.commons.core.storage.min;

import lombok.Data;

import java.io.Serializable;

/**
 * MinIO配置参数
 *
 * @author elvea
 */
@Data
public class MinStorageConfig implements Serializable {
    /**
     * 是否启用
     */
    private boolean enabled = false;
    /**
     * Endpoint
     */
    private String endpoint = "";
    /**
     * Access Key
     */
    private String accessKey = "";
    /**
     * Secret Key
     */
    private String secretKey = "";
    /**
     * BucketName
     */
    private String bucketName = "";
    /**
     * 自定义域名
     */
    private String domain = "";
}
