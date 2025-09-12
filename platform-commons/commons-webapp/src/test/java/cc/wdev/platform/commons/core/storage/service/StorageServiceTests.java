package cc.wdev.platform.commons.core.storage.service;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.platform.commons.core.storage.StorageFactory;
import cc.wdev.platform.commons.core.storage.aws.AwsStorageService;
import cc.wdev.platform.commons.core.storage.domain.FileObject;
import cc.wdev.platform.commons.core.storage.oss.OssStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.nio.file.Paths;

/**
 * @author elvea
 */
public class StorageServiceTests extends BaseTests {

    @Autowired(required = false)
    StorageFactory storage;

    @Test
    public void storageServiceTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("html/tpl.html");

        S3Configuration config = S3Configuration.builder().chunkedEncodingEnabled(false).build();

        S3Client s = S3Client.builder()
            .endpointOverride(URI.create(storage.getConfig().getAws().getEndpoint()))
            .region(Region.of(storage.getConfig().getAws().getRegion()))
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                storage.getConfig().getAws().getAccessKey(), storage.getConfig().getAws().getSecretKey())))
            .forcePathStyle(true)
            .serviceConfiguration(config)
            .build();

        s.putObject(
            PutObjectRequest.builder()
                .bucket("public")
                .key("tpl.html")
                .build(),
            Paths.get(resource.getURI())
        );

        System.out.println("Uploaded hello.txt");
    }

    @Test
    public void awsStorageServiceTest() throws Exception {
        AwsStorageService service = this.storage.getAwsStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        FileObject<?> object = service.uploadFile(resource.getFile());
        Assertions.assertNotNull(object);
    }

    @Test
    public void ossStorageServiceTest() throws Exception {
        OssStorageService service = this.storage.getOssStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        FileObject<?> object = service.uploadFile(resource.getFile());
        Assertions.assertNotNull(object);
    }

}
