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

/**
 * @author elvea
 */
public class StorageServiceTests extends BaseTests {

    @Autowired(required = false)
    StorageFactory storage;

    @Test
    public void awsStorageServiceTest() throws Exception {
        AwsStorageService service = this.storage.getAwsStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        FileObject<?> object = service.uploadFile(resource.getFile());
        Assertions.assertNotNull(object);

        String key = object.getKey();
        FileObject<?> fileObject = service.getFile(key);
        Assertions.assertNotNull(fileObject.getObject());

        FileObject<?> urlFileObject = service.getUrl(key);
        Assertions.assertNotNull(urlFileObject.getUrl());
    }

    @Test
    public void ossStorageServiceTest() throws Exception {
        OssStorageService service = this.storage.getOssStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        FileObject<?> object = service.uploadFile(resource.getFile());
        Assertions.assertNotNull(object);

        String key = object.getKey();
        FileObject<?> fileObject = service.getFile(key);
        Assertions.assertNotNull(fileObject.getUrl());

        FileObject<?> urlFileObject = service.getUrl(key);
        Assertions.assertNotNull(urlFileObject.getUrl());
    }

}
