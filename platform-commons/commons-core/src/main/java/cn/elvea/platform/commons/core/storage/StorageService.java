package cn.elvea.platform.commons.core.storage;

import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;

import java.io.*;

/**
 * 存储服务
 *
 * @author elvea
 * @since 0.0.1
 */
public interface StorageService {

    /**
     * 获取文件信息
     */
    default FileObject<?> getFile(String path) {
        return this.getFile(path, false);
    }

    /**
     * 获取文件信息
     */
    FileObject<?> getFile(String path, boolean withLocalTempFile);

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(byte[] data, String path) throws Exception {
        return this.uploadFile(data, FileParameter.withDefault(), path);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(byte[] data, FileParameter params, String path) throws Exception {
        return uploadFile(new ByteArrayInputStream(data), params, path);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(File file, String path) throws Exception {
        return this.uploadFile(file, FileParameter.withDefault(), path);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(File file, FileParameter params, String path) throws Exception {
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            return this.uploadFile(is, params, path);
        }
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(InputStream is, String path) throws Exception {
        return this.uploadFile(is, FileParameter.withDefault(), path);
    }

    /**
     * 上传文件
     */
    FileObject<?> uploadFile(InputStream is, FileParameter params, String path) throws Exception;

    /**
     * 创建临时文件夹
     *
     * @return File
     */
    File newTempFolder();

    /**
     * 在系统临时目录下创建一个临时文件
     *
     * @param filename 文件路径
     * @return 临时文件
     */
    File newTempFile(String filename) throws Exception;

    /**
     * 生成文件名
     *
     * @param ext 文件后缀名
     * @return String
     */
    String generateExtFilename(String ext);

}
