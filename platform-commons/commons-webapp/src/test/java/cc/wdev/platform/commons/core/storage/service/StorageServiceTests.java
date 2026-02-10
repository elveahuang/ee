package cc.wdev.platform.commons.core.storage.service;

import cc.wdev.dev.webapp.BaseTests;
import cc.wdev.platform.commons.core.storage.StorageFactory;
import cc.wdev.platform.commons.core.storage.StorageService;
import cc.wdev.platform.commons.core.storage.aws.AwsStorageService;
import cc.wdev.platform.commons.core.storage.model.FileObject;
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
        this.baseTest(service);
    }

    @Test
    public void ossStorageServiceTest() throws Exception {
        OssStorageService service = this.storage.getOssStorageService();
        this.baseTest(service);
    }

    private void baseTest(StorageService<?> service) throws Exception {
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        FileObject<?> uploadFileObject = service.uploadFile(resource.getFile());
        Assertions.assertNotNull(uploadFileObject);

        String key = uploadFileObject.getKey();
        FileObject<?> getFileObject = service.getFile(key);
        Assertions.assertNotNull(getFileObject.getObject());

        FileObject<?> getUrlObject = service.getUrl(key);
        Assertions.assertNotNull(getUrlObject.getUrl());
    }

}
