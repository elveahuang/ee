package cc.elvea.platform.commons.core.storage.cos;

import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
public class CosStorageConfig implements Serializable {
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
}
