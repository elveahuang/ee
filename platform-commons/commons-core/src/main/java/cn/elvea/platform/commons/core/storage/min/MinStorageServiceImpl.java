package cn.elvea.platform.commons.core.storage.min;

import cn.elvea.platform.commons.core.exception.ServiceException;
import cn.elvea.platform.commons.core.storage.AbstractStorageService;
import cn.elvea.platform.commons.core.storage.StorageService;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.UploadObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author elvea
 * @see MinStorageService
 * @see StorageService
 * @since 0.0.1
 */
@Slf4j
public class MinStorageServiceImpl extends AbstractStorageService implements MinStorageService {

    private final MinStorageConfig config;

    public MinStorageServiceImpl(MinStorageConfig config) {
        this.config = config;
    }

    /**
     * @see MinStorageService#getClient()
     */
    @Override
    public MinioClient getClient() {
        return MinioClient.builder().endpoint(this.config.getEndpoint())
                .credentials(this.config.getAccessKey(), this.config.getSecretKey()).build();
    }

    /**
     * @see MinStorageService#closeClient(MinioClient)
     */
    @Override
    public void closeClient(MinioClient client) {
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
        return null;
    }

    /**
     * @see StorageService#getFile(String)
     */
    @Override
    public FileObject<?> getFile(String path, boolean withLocalTempFile) {
        MinioClient client = null;
        try {
            client = getClient();

            // 获取云存储文件信息
            GetObjectResponse object = client.getObject(null);

            // 创建本地临时目录文件
            File localTempFile = null;
            if (withLocalTempFile) {
                localTempFile = newTempFile(generateFilename(path));
                try (InputStream is = new FileInputStream(localTempFile)) {
                    FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                }
            }

            // 构建文件信息
            return MinFileObject.builder().object(localTempFile).response(null).build();
        } catch (Exception e) {
            log.error("fail to get oss file with key - {}", path, e);
            throw new ServiceException("Fail to get OSS file.", e);
        } finally {
            this.closeClient(client);
        }
    }

    /**
     * @see StorageService#uploadFile(InputStream, FileParameter, String)
     */
    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter params, String path) throws Exception {
        MinioClient client = null;
        try {
            client = getClient();
            ObjectWriteResponse response = client.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(getBucketName())
                            .object("my-objectname")
                            .filename("my-video.avi")
                            .contentType("video/mp4")
                            .build());
            return MinFileObject.builder().build();
        } finally {
            this.closeClient(client);
        }
    }

}
