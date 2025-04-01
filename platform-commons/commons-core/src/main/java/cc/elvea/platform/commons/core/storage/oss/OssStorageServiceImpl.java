package cc.elvea.platform.commons.core.storage.oss;

import cc.elvea.platform.commons.core.storage.StorageService;
import cc.elvea.platform.commons.core.storage.model.FileObject;
import cc.elvea.platform.commons.core.storage.model.FileParameter;
import cc.elvea.platform.commons.core.storage.utils.StorageUtils;
import cc.elvea.platform.commons.exception.ServiceException;
import cc.elvea.platform.commons.utils.JacksonUtils;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
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
import java.util.Date;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see OssStorageService
 * @see StorageService
 */
@Slf4j
public class OssStorageServiceImpl implements OssStorageService, StorageService {

    private final OssStorageConfig config;

    /**
     * 构造函数
     *
     * @param config 存储设置
     */
    public OssStorageServiceImpl(OssStorageConfig config) {
        this.config = config;
    }

    /**
     * @see OssStorageService#getClient()
     */
    @Override
    public OSS getClient() {
        OSS oss = new OSSClientBuilder().build(this.config.getEndpoint(), this.config.getAccessKeyId(), this.config.getAccessKeySecret());
        if (!oss.doesBucketExist(this.getBucketName())) {
            oss.createBucket(this.getBucketName());
        }
        return oss;
    }

    /**
     * @see OssStorageService#closeClient(OSS)
     */
    @Override
    public void closeClient(OSS client) {
        if (client != null) {
            client.shutdown();
        }
    }

    /**
     * @see OssStorageService#getBucketName()
     */
    @Override
    public String getBucketName() {
        return this.config.getBucketName();
    }

    /**
     * @see OssStorageService#getDomain()
     */
    @Override
    public String getDomain() {
        return this.config.getDomain();
    }

    /**
     * @see OssStorageService#getFile(String, boolean)
     */
    @Override
    public FileObject<?> getFile(String key, boolean withLocalTempFile) {
        OSS client = null;
        try {
            client = getClient();

            // 获取云存储文件信息
            OSSObject object = client.getObject(new GetObjectRequest(getBucketName(), key));
            log.error("OSS getObject response - [{}].", object.getKey());

            //
            String url;
            if (StrUtil.isBlank(getDomain())) {
                Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 24);
                GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(getBucketName(), key);
                request.setExpiration(expiration);
                url = client.generatePresignedUrl(request).toString();
                log.error("OSS getObjectUrl response - [{}].", url);
                url = url.substring(0, url.indexOf("?"));
            } else {
                url = getDomain() + "/" + key;
            }
            log.error("OSS getObjectUrl - [{}].", url);

            // 创建本地临时目录文件
            File localTempFile = null;
            if (withLocalTempFile) {
                localTempFile = StorageUtils.newTempFile(StorageUtils.generateFilename(key));
                try (InputStream is = new FileInputStream(localTempFile)) {
                    FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                }
            }

            // 构建文件信息
            return OssFileObject.builder().object(localTempFile).url(url).response(object).build();
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

            String name = StorageUtils.generateFilename(parameter);
            String path = StorageUtils.generatePath(parameter);
            String key = StorageUtils.generateKey(parameter, name, path);

            PutObjectResult result = client.putObject(this.getBucketName(), key, is);
            log.error("OSS putObject response - [{}].", JacksonUtils.toJson(result));

            return getFile(key, false);
        } finally {
            this.closeClient(client);
        }
    }

}
