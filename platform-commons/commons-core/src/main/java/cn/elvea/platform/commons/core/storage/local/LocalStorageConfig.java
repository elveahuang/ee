package cn.elvea.platform.commons.core.storage.local;

import lombok.Data;

import java.io.Serializable;

/**
 * 本地存储配置
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
public class LocalStorageConfig implements Serializable {
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 存储路径
     */
    private String path = "upload";
    /**
     * 域名
     */
    private String domain = "";
    /**
     * 域名
     */
    private String url = "";
}
