package cn.elvea.platform.commons.core.storage.cos;

import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
public class CosStorageConfig implements Serializable {
    /**
     * 是否启用
     */
    private Boolean enabled;
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
}
