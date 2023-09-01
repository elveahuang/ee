package cn.elvea.platform.commons.core.storage.oss;

import cn.elvea.platform.commons.core.exception.ServiceException;
import cn.elvea.platform.commons.core.storage.AbstractStorageService;
import cn.elvea.platform.commons.core.storage.StorageService;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;
import cn.elvea.platform.commons.core.storage.enums.FileAccessTypeEnum;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.aliyun.oss.model.CannedAccessControlList.Private;
import static com.aliyun.oss.model.CannedAccessControlList.PublicRead;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see OssStorageService
 * @see StorageService
 * @since 0.0.1
 */
@Slf4j
public class OssStorageServiceImpl extends AbstractStorageService implements OssStorageService {

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
    public FileObject<?> getFile(String path, boolean withLocalTempFile) {
        OSS client = null;
        try {
            client = getClient();

            // 获取云存储文件信息
            OSSObject object = client.getObject(new GetObjectRequest(getBucketName(), path));

            // 创建本地临时目录文件
            File localTempFile = null;
            if (withLocalTempFile) {
                localTempFile = newTempFile(generateFilename(path));
                try (InputStream is = new FileInputStream(localTempFile)) {
                    FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                }
            }

            // 构建文件信息
            return OssFileObject.builder().object(localTempFile).response(object).build();
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
        OSS client = null;
        try {
            client = getClient();

            // 上传文件
            PutObjectResult result = client.putObject(this.getBucketName(), path, is);

            // 设置权限
            if (params != null && FileAccessTypeEnum.PRIVATE.equals(params.getAccessType())) {
                client.setObjectAcl(this.getBucketName(), path, Private);
            } else {
                client.setObjectAcl(this.getBucketName(), path, PublicRead);
            }

            return OssFileObject.builder().key(path).build();
        } finally {
            this.closeClient(client);
        }
    }

}
