package cc.wdev.platform.commons.core.storage;

import cc.wdev.platform.commons.core.storage.domain.FileObject;
import cc.wdev.platform.commons.core.storage.domain.FileParameter;
import cc.wdev.platform.commons.core.storage.domain.GenerateUrlRequest;
import cc.wdev.platform.commons.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 存储服务
 *
 * @author elvea
 */
public interface StorageService {

    /**
     * 获取文件链接
     */
    default FileObject<?> getUrl(String key) {
        return this.getUrl(GenerateUrlRequest.builder().key(key).build());
    }

    /**
     * 获取文件链接
     */
    FileObject<?> getUrl(GenerateUrlRequest request);

    /**
     * 获取文件信息
     */
    FileObject<?> getFile(String key);

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(MultipartFile file) throws Exception {
        FileParameter parameter = FileParameter.builder()
            .originalFilename(file.getOriginalFilename())
            .contentType(file.getContentType())
            .size(file.getSize())
            .build();
        return this.uploadFile(file.getInputStream(), parameter);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(MultipartFile file, FileParameter parameter) throws Exception {
        return this.uploadFile(file.getInputStream(), parameter);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(File file) throws Exception {
        FileParameter parameter = FileParameter.builder()
            .originalFilename(file.getName())
            .contentType(FileUtils.getContentType(file))
            .size(FileUtils.getFileSize(file))
            .build();
        return this.uploadFile(file, parameter);
    }

    /**
     * 上传文件
     */
    default FileObject<?> uploadFile(File file, FileParameter parameter) throws Exception {
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            return this.uploadFile(is, parameter);
        }
    }

    /**
     * 上传文件
     */
    FileObject<?> uploadFile(InputStream is, FileParameter parameter) throws Exception;

    /**
     * 下载文件
     */
    default void download(String key, OutputStream out) {
    }

}
