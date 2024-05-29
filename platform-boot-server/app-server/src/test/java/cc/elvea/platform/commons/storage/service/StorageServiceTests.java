package cc.elvea.platform.commons.storage.service;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.sequence.Sequence;
import cc.elvea.platform.commons.storage.Storage;
import cc.elvea.platform.commons.storage.domain.FileObject;
import cc.elvea.platform.commons.storage.min.MinStorageService;
import cc.elvea.platform.commons.storage.oss.OssStorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * @author elvea
 * @since 24.1.0
 */
public class StorageServiceTests extends BaseTests {

    @Autowired()
    Sequence sequence;

    @Autowired(required = false)
    Storage storage;

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
