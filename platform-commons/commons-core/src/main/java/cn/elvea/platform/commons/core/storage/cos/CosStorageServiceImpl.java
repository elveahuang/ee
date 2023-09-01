package cn.elvea.platform.commons.core.storage.cos;

import cn.elvea.platform.commons.core.exception.ServiceException;
import cn.elvea.platform.commons.core.storage.AbstractStorageService;
import cn.elvea.platform.commons.core.storage.StorageService;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;
import cn.elvea.platform.commons.core.storage.enums.FileAccessTypeEnum;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.qcloud.cos.model.CannedAccessControlList.Private;
import static com.qcloud.cos.model.CannedAccessControlList.PublicRead;

/**
 * 阿里云存储服务
 *
 * @author elvea
 * @see CosStorageService
 * @see StorageService
 * @since 0.0.1
 */
@Slf4j
public class CosStorageServiceImpl extends AbstractStorageService implements CosStorageService {

    private final CosStorageConfig config;

    /**
     * 构造函数
     *
     * @param config 存储设置
     */
    public CosStorageServiceImpl(CosStorageConfig config) {
        this.config = config;
    }

    /**
     * @see CosStorageService#getClient()
     */
    @Override
    public COSClient getClient() {
        COSCredentials credentials = new BasicCOSCredentials(this.config.getAccessKey(), this.config.getSecretKey());
        Region region = new Region(this.config.getEndpoint());
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(credentials, clientConfig);
    }

    /**
     * @see CosStorageService#closeClient(COSClient)
     */
    @Override
    public void closeClient(COSClient client) {
        if (client != null) {
            client.shutdown();
        }
    }

    /**
     * @see CosStorageService#getBucketName()
     */
    @Override
    public String getBucketName() {
        return this.config.getBucketName();
    }

    /**
     * @see CosStorageService#getDomain()
     */
    @Override
    public String getDomain() {
        return null;
    }

    /**
     * @see StorageService#getFile(String, boolean)
     */
    @Override
    public FileObject<?> getFile(String path, boolean withLocalTempFile) {
        COSClient client = null;
        try {
            client = getClient();

            // 获取云存储文件信息
            COSObject object = client.getObject(getBucketName(), path);

            // 创建本地临时目录文件
            File localTempFile = null;
            if (withLocalTempFile) {
                localTempFile = newTempFile(generateFilename(path));
                try (InputStream is = new FileInputStream(localTempFile)) {
                    FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                }
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
     * @see StorageService#uploadFile(InputStream, FileParameter, String)
     */
    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter params, String path) {
        COSClient client = null;
        try {
            String key = "";

            client = this.getClient();
            client.putObject(getBucketName(), key, is, null);
            if (params != null && FileAccessTypeEnum.PRIVATE.equals(params.getAccessType())) {
                client.setObjectAcl(this.getBucketName(), key, Private);
            } else {
                client.setObjectAcl(this.getBucketName(), key, PublicRead);
            }
            return CosFileObject.builder().build();
        } finally {
            this.closeClient(client);
        }
    }

}
