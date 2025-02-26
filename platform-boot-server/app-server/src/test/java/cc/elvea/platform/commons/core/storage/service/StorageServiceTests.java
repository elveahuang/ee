package cc.elvea.platform.commons.core.storage.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.core.sequence.Sequence;
import cc.elvea.platform.commons.core.storage.StorageFactory;
import cc.elvea.platform.commons.core.storage.min.MinStorageService;
import cc.elvea.platform.commons.core.storage.model.FileObject;
import cc.elvea.platform.commons.core.storage.oss.OssStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * @author elvea
 */
public class StorageServiceTests extends BaseTests {

    @Autowired()
    Sequence sequence;

    @Autowired(required = false)
    StorageFactory storage;

    @Test
    public void minStorageServiceTest() throws Exception {
        MinStorageService service = this.storage.getMinStorageService();
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
