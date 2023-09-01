package cn.elvea.platform.commons.core.storage.domain;

import cn.elvea.platform.commons.core.storage.enums.StorageType;

import java.io.File;
import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface FileObject<R> extends Serializable {

    /**
     * 存储类型
     */
    StorageType getStorageType();

    /**
     * 文件标识
     */
    String getKey();

    /**
     * 文件对象
     */
    File getObject();

    /**
     *
     */
    R getResponse();

}
