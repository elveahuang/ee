package cn.elvea.platform.commons.core.storage.manager;

import cn.elvea.platform.commons.core.storage.StorageConfig;
import cn.elvea.platform.commons.core.storage.StorageService;
import cn.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.core.storage.cos.CosStorageService;
import cn.elvea.platform.commons.core.storage.cos.CosStorageServiceImpl;
import cn.elvea.platform.commons.core.storage.local.LocalStorageConfig;
import cn.elvea.platform.commons.core.storage.local.LocalStorageService;
import cn.elvea.platform.commons.core.storage.local.LocalStorageServiceImpl;
import cn.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.core.storage.min.MinStorageService;
import cn.elvea.platform.commons.core.storage.min.MinStorageServiceImpl;
import cn.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import cn.elvea.platform.commons.core.storage.oss.OssStorageService;
import cn.elvea.platform.commons.core.storage.oss.OssStorageServiceImpl;

/**
 * @author elvea
 * @see StorageManager
 * @since 0.0.1
 */
public class DefaultStorageManager implements StorageManager {

    private final StorageConfig config;

    public DefaultStorageManager(StorageConfig config) {
        this.config = config;
    }

    @Override
    public StorageService getStorageService() {
        return switch (this.config.getType()) {
            case COS -> getCosStorageService();
            case OSS -> getOssStorageService();
            case MIN -> getMinStorageService();
            default -> getLocalStorageService();
        };
    }

    /**
     * @see StorageManager#getLocalStorageService()
     */
    @Override
    public LocalStorageService getLocalStorageService() {
        return this.getLocalStorageService(this.config.getLocal());
    }

    /**
     * @see StorageManager#getLocalStorageService(LocalStorageConfig)
     */
    @Override
    public LocalStorageService getLocalStorageService(LocalStorageConfig config) {
        return new LocalStorageServiceImpl(config);
    }

    /**
     * @see StorageManager#getMinStorageService()
     */
    @Override
    public MinStorageService getMinStorageService() {
        return this.getMinStorageService(this.config.getMin());
    }

    /**
     * @see StorageManager#getMinStorageService(MinStorageConfig)
     */
    @Override
    public MinStorageService getMinStorageService(MinStorageConfig config) {
        return new MinStorageServiceImpl(config);
    }

    /**
     * @see StorageManager#getCosStorageService()
     */
    @Override
    public OssStorageService getOssStorageService() {
        return this.getOssStorageService(this.config.getOss());
    }

    /**
     * @see StorageManager#getOssStorageService()
     */
    @Override
    public OssStorageService getOssStorageService(OssStorageConfig config) {
        return new OssStorageServiceImpl(config);
    }

    /**
     * @see StorageManager#getCosStorageService()
     */
    @Override
    public CosStorageService getCosStorageService() {
        return this.getCosStorageService(this.config.getCos());
    }

    /**
     * @see StorageManager#getCosStorageService(CosStorageConfig)
     */
    @Override
    public CosStorageService getCosStorageService(CosStorageConfig config) {
        return new CosStorageServiceImpl(config);
    }

}
