package cc.wdev.platform.commons.core.storage.oss;

import cc.wdev.platform.commons.core.storage.StorageService;
import cc.wdev.platform.commons.core.storage.StorageUtils;
import cc.wdev.platform.commons.core.storage.model.FileObject;
import cc.wdev.platform.commons.core.storage.model.FileParameter;
import cc.wdev.platform.commons.core.storage.model.FileUploadResult;
import cc.wdev.platform.commons.core.storage.model.GenerateUrlRequest;
import cc.wdev.platform.commons.exception.ServiceException;
import cc.wdev.platform.commons.utils.JacksonUtils;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.comm.Protocol;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see OssStorageService
 * @see StorageService
 */
@Slf4j
public record OssStorageService(OssStorageConfig config) implements StorageService<OSS> {

    /**
     * @see StorageService#getClient()
     */
    @Override
    public OSS getClient() {
        log.info("Create OSS Client. {}", config.getAccessKeyId());

        ClientBuilderConfiguration configuration = new ClientBuilderConfiguration();
        configuration.setSignatureVersion(SignVersion.V4);
        configuration.setProtocol(Protocol.HTTPS);
        configuration.setSupportCname(true);

        CredentialsProvider credentialsProvider = CredentialsProviderFactory.newDefaultCredentialProvider(
            this.config.getAccessKeyId(), this.config.getAccessKeySecret());

        return OSSClientBuilder.create()
            .endpoint(this.config.getEndpoint())
            .region(this.config.getRegion())
            .credentialsProvider(credentialsProvider)
            .clientConfiguration(configuration)
            .build();
    }

    /**
     * @see StorageService#closeClient(Object)
     */
    @Override
    public void closeClient(OSS client) {
        if (client != null) {
            client.shutdown();
        }
    }

    /**
     * @see StorageService#getBucket()
     */
    @Override
    public String getBucket() {
        return this.config.getBucket();
    }

    /**
     * @see StorageService#getEndpoint()
     */
    @Override
    public String getEndpoint() {
        return this.config.getEndpoint();
    }

    /**
     * @see StorageService#getDomain()
     */
    @Override
    public String getDomain() {
        return this.config.getDomain();
    }

    /**
     * @see StorageService#getUrl(String)
     */
    @Override
    public FileObject<?> getUrl(GenerateUrlRequest request) {
        OSS client = null;
        try {
            client = getClient();

            // 构建请求参数
            Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24);
            GeneratePresignedUrlRequest presignedUrlRequest = new GeneratePresignedUrlRequest(getBucket(), request.getKey());
            presignedUrlRequest.setExpiration(expiration);

            // 生成链接
            String url = client.generatePresignedUrl(presignedUrlRequest).toString();

            return OssFileObject.builder().key(request.getKey()).url(url).build();
        } catch (Exception e) {
            log.error("OSS getFile failed.", e);
            throw new ServiceException("OSS getFile failed.", e);
        } finally {
            this.closeClient(client);
        }
    }

    /**
     * @see StorageService#getFile(String)
     */
    @Override
    public FileObject<?> getFile(String key) {
        OSS client = null;
        try {
            client = getClient();

            // 获取云存储文件信息
            OSSObject object = client.getObject(new GetObjectRequest(getBucket(), key));
            log.error("OSS getObject response - [{}]", object.getKey());

            // 本地临时文件
            File localTempFile = StorageUtils.newTempFile(StorageUtils.generateFilename(key));
            try (InputStream is = new FileInputStream(localTempFile)) {
                FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
            }

            return OssFileObject.builder().object(localTempFile).key(key).response(object).build();
        } catch (Exception e) {
            log.error("OSS getFile failed.", e);
            throw new ServiceException("OSS getFile failed.", e);
        } finally {
            this.closeClient(client);
        }
    }

    /**
     * @see StorageService#uploadFile(InputStream, FileParameter)
     */
    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter parameter) throws Exception {
        OSS client = null;
        try {
            client = getClient();

            // 处理请求参数
            String key = StorageUtils.generateFileKey(parameter);

            // 上传文件
            PutObjectResult response = client.putObject(this.getBucket(), key, is);
            log.info("OSS putObject response - [{}].", JacksonUtils.toJson(response));

            // 处理响应结果
            FileUploadResult result = FileUploadResult.builder().key(key).build();
            return OssFileObject.builder().response(response).result(result).key(key).build();
        } finally {
            this.closeClient(client);
        }
    }

    /**
     * @see StorageService#download(String, OutputStream)
     */
    @Override
    public void download(String key, OutputStream out) {
        OSS client = null;
        try {
            client = getClient();

            OSSObject object = client.getObject(new GetObjectRequest(getBucket(), key));
            log.info("OSS getObject download response - [{}].", JacksonUtils.toJson(object.getResponse()));

            try (InputStream is = object.getObjectContent()) {
                is.transferTo(out);
            }
        } catch (Exception e) {
            throw new ServiceException("Fail to download OSS file.", e);
        } finally {
            this.closeClient(client);
        }
    }

}
