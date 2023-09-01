package cn.elvea.platform.commons.core.storage.manager;

import cn.elvea.platform.commons.core.storage.StorageService;
import cn.elvea.platform.commons.core.storage.cos.CosStorageConfig;
import cn.elvea.platform.commons.core.storage.cos.CosStorageService;
import cn.elvea.platform.commons.core.storage.local.LocalStorageConfig;
import cn.elvea.platform.commons.core.storage.local.LocalStorageService;
import cn.elvea.platform.commons.core.storage.min.MinStorageConfig;
import cn.elvea.platform.commons.core.storage.min.MinStorageService;
import cn.elvea.platform.commons.core.storage.oss.OssStorageConfig;
import cn.elvea.platform.commons.core.storage.oss.OssStorageService;

/**
 * 存储服务管理器
 *
 * @author elvea
 * @since 0.0.1
 */
public interface StorageManager {

    StorageService getStorageService();

    /**
     * @return {@link LocalStorageService}
     */
    LocalStorageService getLocalStorageService();

    /**
     * @return {@link LocalStorageService}
     */
    LocalStorageService getLocalStorageService(LocalStorageConfig config);

    /**
     * @return {@link MinStorageService}
     */
    MinStorageService getMinStorageService();

    /**
     * @return {@link MinStorageService}
     */
    MinStorageService getMinStorageService(MinStorageConfig config);

    /**
     * @return {@link OssStorageService}
     */
    OssStorageService getOssStorageService();

    /**
     * @return {@link OssStorageService}
     */
    OssStorageService getOssStorageService(OssStorageConfig config);

    /**
     * @return {@link CosStorageService}
     */
    CosStorageService getCosStorageService();

    /**
     * @return {@link CosStorageService}
     */
    CosStorageService getCosStorageService(CosStorageConfig config);

}
