package cc.wdev.platform.commons.core.storage.aws;

import cc.wdev.platform.commons.core.storage.StorageService;
import cc.wdev.platform.commons.core.storage.StorageUtils;
import cc.wdev.platform.commons.core.storage.domain.FileObject;
import cc.wdev.platform.commons.core.storage.domain.FileParameter;
import cc.wdev.platform.commons.exception.ServiceException;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.async.BlockingInputStreamAsyncRequestBody;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedUpload;
import software.amazon.awssdk.transfer.s3.model.Upload;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * 亚马逊存储服务
 *
 * @author elvea
 * @see AwsStorageService
 * @see StorageService
 */
@Slf4j
public record AwsStorageServiceImpl(AwsStorageConfig config) implements AwsStorageService, StorageService {

    /**
     * @see AwsStorageService#getClient()
     */
    @Override
    public S3AsyncClient getClient() {
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create(this.config.getAccessKey(), this.config.getSecretKey()));

        return S3AsyncClient.builder()
            .credentialsProvider(credentialsProvider)
            .region(Region.of(this.config.getRegion()))
            .endpointOverride(URI.create(this.config.getEndpoint()))
            .httpClient(NettyNioAsyncHttpClient.builder()
                .connectionTimeout(Duration.ofSeconds(60))
                .build())
            .serviceConfiguration(S3Configuration.builder()
                .pathStyleAccessEnabled(this.config.isPathStyleEnabled())
                .chunkedEncodingEnabled(this.config.isChunkedEncodingEnabled())
                .build())
            .build();
    }

    /**
     * @see AwsStorageService#getTransferManager(S3AsyncClient)
     */
    @Override
    public S3TransferManager getTransferManager(S3AsyncClient client) {
        return S3TransferManager.builder().s3Client(client).build();
    }

    /**
     * @see AwsStorageService#closeClient(S3AsyncClient)
     */
    @Override
    public void closeClient(S3AsyncClient client) {
        if (client != null) {
            client.close();
        }
    }

    /**
     * @see AwsStorageService#getBucketName()
     */
    @Override
    public String getBucketName() {
        return this.config.getBucketName();
    }

    /**
     * @see AwsStorageService#getDomain()
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
        S3AsyncClient client = null;
        try {
            client = getClient();

            // 获取云存储文件信息
            GetObjectRequest request = GetObjectRequest.builder()
                .bucket(this.config.getBucketName())
                .key(path).build();
            CompletableFuture<GetObjectResponse> future = client.getObject(request, Paths.get(path));
            future.whenComplete((response, e) -> {
                try {
                    if (response != null) {
                        System.out.println("Object downloaded. Details: " + response);
                    } else {
                        log.error("Object error. ", e);
                    }
                } finally {
                    log.info("Object downloaded. Details: %s");
                }
            });
            future.join();

            // 创建本地临时目录文件
            File localTempFile = null;
            if (withLocalTempFile) {
                localTempFile = StorageUtils.newTempFile(StorageUtils.generateFilename(path));
                try (InputStream is = new FileInputStream(localTempFile)) {
                    FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                }
            }

            // 构建文件信息
            return AwsFileObject.builder().key(path).object(localTempFile).build();
        } catch (Exception e) {
            log.error("fail to get aws file with key - {}", path, e);
            throw new ServiceException("Fail to get AWS file.", e);
        } finally {
            this.closeClient(client);
        }
    }

    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter parameter) {
        S3AsyncClient client = null;
        S3TransferManager transferManager = null;
        try {
            if (!(is instanceof ByteArrayInputStream)) {
                is = new ByteArrayInputStream(IoUtil.readBytes(is));
            }

            client = this.getClient();
            transferManager = this.getTransferManager(client);

            String key = StorageUtils.generateFileKey(parameter);

            BlockingInputStreamAsyncRequestBody body = BlockingInputStreamAsyncRequestBody.builder()
                .contentLength(parameter.getSize())
                .subscribeTimeout(Duration.ofSeconds(120))
                .build();

            Upload upload = transferManager.upload(builder -> builder.requestBody(body)
                .addTransferListener(LoggingTransferListener.create())
                .putObjectRequest(y -> y.bucket(this.config.getBucketName())
                    .key(key)
                    .contentType(parameter.getContentType())
                    .build())
                .build());
            body.writeInputStream(is);

            CompletedUpload uploadResult = upload.completionFuture().join();
            String eTag = uploadResult.response().eTag();
            return AwsFileObject.builder().build();
        } finally {
            this.closeClient(client);
        }
    }

}
