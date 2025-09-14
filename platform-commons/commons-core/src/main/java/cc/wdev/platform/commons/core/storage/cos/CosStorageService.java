package cc.wdev.platform.commons.core.storage.cos;

import cc.wdev.platform.commons.core.storage.StorageService;
import cc.wdev.platform.commons.core.storage.StorageUtils;
import cc.wdev.platform.commons.core.storage.model.FileObject;
import cc.wdev.platform.commons.core.storage.model.FileParameter;
import cc.wdev.platform.commons.core.storage.model.FileUploadResult;
import cc.wdev.platform.commons.core.storage.model.GenerateUrlRequest;
import cc.wdev.platform.commons.exception.ServiceException;
import cc.wdev.platform.commons.utils.JacksonUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see StorageService
 */
@Slf4j
public record CosStorageService(CosStorageConfig config) implements StorageService<COSClient> {

    /**
     * @see StorageService#getClient()
     */
    @Override
    public COSClient getClient() {
        log.info("Create COS Client. {}", config.getAccessKey());

        COSCredentials credentials = new BasicCOSCredentials(this.config.getAccessKey(), this.config.getSecretKey());
        Region region = new Region(this.config.getEndpoint());
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(credentials, clientConfig);
    }

    /**
     * @see StorageService#closeClient(Object)
     */
    @Override
    public void closeClient(COSClient client) {
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
        return null;
    }

    /**
     * @see StorageService#getUrl(GenerateUrlRequest)
     */
    @Override
    public FileObject<?> getUrl(GenerateUrlRequest request) {
        return null;
    }

    /**
     * @see StorageService#getFile(String)
     */
    @Override
    public FileObject<?> getFile(String path) {
        COSClient client = null;
        try {
            client = getClient();

            // 获取云存储文件信息
            COSObject object = client.getObject(getBucket(), path);

            // 创建本地临时目录文件
            File localTempFile = StorageUtils.newTempFile(StorageUtils.generateFilename(path));
            try (InputStream is = new FileInputStream(localTempFile)) {
                FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
            }

            // 构建文件信息
            return CosFileObject.builder().key(path).object(localTempFile).response(object).build();
        } catch (Exception e) {
            log.error("fail to get cos file with key - {}", path, e);
            throw new ServiceException("Fail to get COS file.", e);
        } finally {
            this.closeClient(client);
        }
    }

    /**
     * @see StorageService#uploadFile(InputStream, FileParameter)
     */
    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter parameter) throws Exception {
        COSClient client = null;
        try {
            client = this.getClient();

            // 处理请求参数
            String key = StorageUtils.generateFileKey(parameter);

            // 上传文件
            PutObjectResult response = client.putObject(getBucket(), key, is, null);
            log.info("OSS putObject response - [{}].", JacksonUtils.toJson(response));

            // 处理响应结果
            FileUploadResult result = FileUploadResult.builder().key(key).build();
            return CosFileObject.builder().result(result).key(key).build();
        } finally {
            this.closeClient(client);
        }
    }

    /**
     * @see StorageService#download(String, OutputStream)
     */
    @Override
    public void download(String key, OutputStream out) {
        COSClient client = null;
        try {
            client = getClient();

            COSObject object = client.getObject(getBucket(), key);
            log.info("OSS getObject download response - [{}].", JacksonUtils.toJson(object.getKey()));

            try (InputStream is = object.getObjectContent()) {
                is.transferTo(out);
            }
        } catch (Exception e) {
            throw new ServiceException("Fail to download COS file.", e);
        } finally {
            this.closeClient(client);
        }
    }

}
