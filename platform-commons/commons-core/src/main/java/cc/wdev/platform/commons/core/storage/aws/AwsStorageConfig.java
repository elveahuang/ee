package cc.wdev.platform.commons.core.storage.aws;

import lombok.Data;
import software.amazon.awssdk.regions.Region;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
public class AwsStorageConfig implements Serializable {
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
     * Region
     */
    private String region = Region.US_EAST_1.toString();
    /**
     * BucketName
     */
    private String bucketName = "";
}
