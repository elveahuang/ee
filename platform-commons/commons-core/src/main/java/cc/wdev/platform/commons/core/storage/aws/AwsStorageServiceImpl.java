package cc.wdev.platform.commons.core.storage.aws;

import cc.wdev.platform.commons.core.storage.StorageService;
import cc.wdev.platform.commons.core.storage.StorageUtils;
import cc.wdev.platform.commons.core.storage.domain.FileObject;
import cc.wdev.platform.commons.core.storage.domain.FileParameter;
import cc.wdev.platform.commons.core.storage.domain.GenerateUrlRequest;
import cc.wdev.platform.commons.exception.ServiceException;
import cc.wdev.platform.commons.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.time.Duration;

/**
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
    public S3Client getClient() {
        return S3Client.builder()
            .endpointOverride(URI.create(this.config.getEndpoint()))
            .region(Region.of(this.config.getRegion()))
            .credentialsProvider(this.getCredentialsProvider())
            .serviceConfiguration(this.getS3Configuration())
            .build();
    }

    /**
     * @see AwsStorageService#closeClient(S3Client)
     */
    @Override
    public void closeClient(S3Client client) {
        if (client != null) {
            client.close();
        }
    }

    /**
     * @see AwsStorageService#getBucketName()
     */
    @Override
    public String getBucketName() {
        return this.config.getBucket();
    }

    /**
     * @see AwsStorageService#getDomain()
     */
    @Override
    public String getDomain() {
        return null;
    }

    @Override
    public FileObject<?> getUrl(GenerateUrlRequest request) {
        try (S3Presigner s3Presigner = getS3Presigner()) {
            GetObjectRequest objectRequest = GetObjectRequest.builder()
                .bucket(this.getBucketName())
                .key(request.getKey())
                .build();

            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(objectRequest)
                .build();

            PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);
            log.info("Presigned URL: [{}]", presignedRequest.url().toString());
            log.info("HTTP method: [{}]", presignedRequest.httpRequest().method());
            return AwsFileObject.builder().key(request.getKey()).url(presignedRequest.url().toExternalForm()).build();
        }
    }

    /**
     * @see StorageService#getFile(String)
     */
    @Override
    public FileObject<?> getFile(String path) {
        try (S3Client client = getClient()) {
            GetObjectRequest request = GetObjectRequest.builder()
                .bucket(this.config.getBucket())
                .key(path)
                .build();

            try (ResponseInputStream<GetObjectResponse> is = client.getObject(request)) {
                File localTempFile = StorageUtils.newTempFile(StorageUtils.generateFilename(path));
                FileUtils.writeByteArrayToFile(localTempFile, IOUtils.toByteArray(is));
                // 构建文件信息
                return AwsFileObject.builder().key(path).object(localTempFile).build();
            }
        } catch (Exception e) {
            throw new ServiceException("Fail to get AWS file.", e);
        }
    }

    /**
     * @see StorageService#uploadFile(InputStream, FileParameter)
     */
    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter parameter) throws Exception {
        try (S3Client client = this.getClient()) {
            // 处理上传文件参数
            String key = StorageUtils.generateFileKey(parameter);
            PutObjectRequest request = PutObjectRequest.builder()
                .key(key)
                .bucket(this.config.getBucket())
                .build();
            RequestBody body = RequestBody.fromInputStream(is, parameter.getSize());

            // 上传文件
            PutObjectResponse response = client.putObject(request, body);
            log.info("AWS putObject response - [{}].", JacksonUtils.toJson(response));

            return AwsFileObject.builder().key(key).response(response).build();
        }
    }

    /**
     * @see StorageService#download(String, OutputStream)
     */
    @Override
    public void download(String path, OutputStream os) {
        try (S3Client client = this.getClient()) {
            GetObjectRequest request = GetObjectRequest.builder()
                .bucket(this.config.getBucket())
                .key(path)
                .build();
            try (ResponseInputStream<GetObjectResponse> is = client.getObject(request)) {
                log.info("AWS getObject download response - [{}].", JacksonUtils.toJson(is.response()));

                is.transferTo(os);
            }
        } catch (Exception e) {
            throw new ServiceException("Fail to download AWS file.", e);
        }
    }

    private S3Presigner getS3Presigner() {
        return S3Presigner.builder()
            .s3Client(getClient())
            .endpointOverride(URI.create(this.config.getEndpoint()))
            .region(Region.of(this.config.getRegion()))
            .credentialsProvider(this.getCredentialsProvider())
            .serviceConfiguration(this.getS3Configuration())
            .build();
    }

    private S3Configuration getS3Configuration() {
        return S3Configuration.builder()
            .pathStyleAccessEnabled(this.config.isPathStyleEnabled())
            .chunkedEncodingEnabled(this.config.isChunkedEncodingEnabled())
            .build();
    }

    private AwsCredentialsProvider getCredentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(this.config.getAccessKey(), this.config.getSecretKey()));
    }

}
