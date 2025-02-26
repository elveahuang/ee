package cc.elvea.platform.commons.core.storage.min;

import cc.elvea.platform.commons.core.storage.StorageService;
import cc.elvea.platform.commons.core.storage.model.FileObject;
import cc.elvea.platform.commons.core.storage.model.FileParameter;
import cc.elvea.platform.commons.core.storage.utils.StorageUtils;
import cc.elvea.platform.commons.exception.ServiceException;
import cc.elvea.platform.commons.utils.JacksonUtils;
import cn.hutool.core.util.StrUtil;
import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static io.minio.ObjectWriteArgs.MIN_MULTIPART_SIZE;

/**
 * @author elvea
 * @see MinStorageService
 * @see StorageService
 */
@Slf4j
public class MinStorageServiceImpl implements MinStorageService, StorageService {

    private final MinStorageConfig config;

    public MinStorageServiceImpl(MinStorageConfig config) {
        this.config = config;
    }

    /**
     * @see MinStorageService#getClient()
     */
    @Override
    public MinioClient getClient() {
        return MinioClient.builder()
                .endpoint(this.config.getEndpoint())
                .credentials(this.config.getAccessKey(), this.config.getSecretKey())
                .build();
    }

    /**
     * @see MinStorageService#closeClient(MinioClient)
     */
    @Override
    public void closeClient(MinioClient client) {
        log.info("Close Minio Client.");
    }

    /**
     * @see MinStorageService#getBucketName()
     */
    @Override
    public String getBucketName() {
        return this.config.getBucketName();
    }

    /**
     * @see MinStorageService#getDomain()
     */
    @Override
    public String getDomain() {
        return this.config.getDomain();
    }

    /**
     * @see StorageService#getFile(String)
     */
    @Override
    public FileObject<?> getFile(String key, boolean withLocalFile) {
        MinioClient client = null;
        try {
            client = getClient();

            GetObjectArgs args = GetObjectArgs.builder().bucket(getBucketName()).object(key).object(key).build();

            try (GetObjectResponse response = client.getObject(args)) {
                log.error("Minio getObject response - [{}].", response.object());

                // 获取文件链接
                String url;
                if (StrUtil.isBlank(getDomain())) {
                    GetPresignedObjectUrlArgs urlArgs = GetPresignedObjectUrlArgs.builder()
                            .bucket(getBucketName())
                            .object(key)
                            .method(Method.GET)
                            .build();
                    url = client.getPresignedObjectUrl(urlArgs);
                    log.error("Minio getObjectUrl response - [{}].", url);
                    url = url.substring(0, url.indexOf("?"));
                } else {
                    url = getDomain() + "/" + key;
                }
                log.error("Minio getObjectUrl - [{}].", url);

                // 创建本地临时目录文件
                File localTempFile = null;
                if (withLocalFile) {
                    localTempFile = StorageUtils.newTempFile(StorageUtils.generateFilename(key));
                    try (InputStream is = new FileInputStream(localTempFile)) {
                        FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                    }
                }

                // 构建文件信息
                GenericResponse genericResponse = new GenericResponse(response.headers(), response.bucket(), response.region(), response.object());
                return MinFileObject.builder()
                        .key(key)
                        .object(localTempFile)
                        .url(url)
                        .response(genericResponse)
                        .build();
            }
        } catch (Exception e) {
            throw new ServiceException("Minio getFile failed.", e);
        } finally {
            this.closeClient(client);
        }
    }

    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter parameter) {
        MinioClient client = null;
        try {
            client = getClient();

            String name = StorageUtils.generateFilename(parameter);
            String path = StorageUtils.generatePath(parameter);
            String key = StorageUtils.generateKey(parameter, name, path);

            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(getBucketName())
                    .stream(is, parameter.getSize(), MIN_MULTIPART_SIZE)
                    .contentType(parameter.getContentType())
                    .object(key)
                    .build();
            ObjectWriteResponse response = client.putObject(args);
            log.info("Minio putObject response - [{}].", JacksonUtils.toJson(response));

            return getFile(key, false);
        } catch (Exception e) {
            throw new ServiceException("Minio getFile failed.", e);
        } finally {
            this.closeClient(client);
        }
    }

}
