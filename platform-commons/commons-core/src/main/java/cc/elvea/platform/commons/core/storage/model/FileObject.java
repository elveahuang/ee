package cc.elvea.platform.commons.core.storage.model;

import cc.elvea.platform.commons.enums.StorageTypeEnum;

import java.io.File;
import java.io.Serializable;

/**
 * @author elvea
 */
public interface FileObject<R> extends Serializable {

    /**
     * 存储类型
     */
    StorageTypeEnum getStorageType();

    /**
     * 文件标识
     */
    String getKey();

    /**
     * 文件链接
     */
    String getUrl();

    /**
     * 文件对象
     */
    File getObject();

    /**
     * 服务响应
     */
    R getResponse();

}
