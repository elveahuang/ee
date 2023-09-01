package cn.elvea.platform.commons.core.storage.service;

import cn.elvea.platform.BaseTests;
import cn.elvea.platform.commons.core.sequence.Sequence;
import cn.elvea.platform.commons.core.storage.local.LocalStorageService;
import cn.elvea.platform.commons.core.storage.manager.StorageManager;
import cn.elvea.platform.commons.core.storage.oss.OssStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * @author elvea
 * @since 0.0.1
 */
public class StorageServiceTests extends BaseTests {

    @Autowired()
    Sequence sequence;

    @Autowired(required = false)
    StorageManager storageManager;

    @Test
    public void localStorageServiceTest() throws Exception {
        LocalStorageService service = this.storageManager.getLocalStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");

        service.uploadFile(resource.getFile(), sequence.nextIdAsString() + "/html/tpl.html");
        service.uploadFile(resource.getInputStream(), sequence.nextIdAsString() + "/html/tpl.html");
        service.uploadFile(resource.getContentAsByteArray(), sequence.nextIdAsString() + "/html/tpl.html");
    }

    @Test
    public void ossStorageServiceTest() throws Exception {
        OssStorageService service = this.storageManager.getOssStorageService();
        Assertions.assertNotNull(service);

        ClassPathResource resource = new ClassPathResource("html/tpl.html");
        service.uploadFile(resource.getFile(), sequence.nextIdAsString() + "/html/tpl.html");
        service.uploadFile(resource.getInputStream(), sequence.nextIdAsString() + "/html/tpl.html");
        service.uploadFile(resource.getContentAsByteArray(), sequence.nextIdAsString() + "/html/tpl.html");
    }

}
