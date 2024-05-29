package cc.elvea.platform.commons.extensions.facebody;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.oapis.facebody.FaceBodyManager;
import cc.elvea.platform.commons.oapis.facebody.FaceBodyResult;
import cn.hutool.core.codec.Base64;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class FaceBodyServiceTests extends BaseTests {

    @Autowired
    private FaceBodyManager faceBodyManager;

    @Test
    public void detectFaceTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("images/user.jpg");
        String image = Base64.encode(ByteStreams.toByteArray(resource.getInputStream()));
        FaceBodyResult result = this.faceBodyManager.getAliyunFaceBodyService().detectFace(image);
        Assertions.assertFalse(result.isSuccess());
    }

    @Test
    public void compareFaceTest() throws Exception {
        ClassPathResource resource = new ClassPathResource("images/user.jpg");
        String image = Base64.encode(ByteStreams.toByteArray(resource.getInputStream()));
        FaceBodyResult result = this.faceBodyManager.getAliyunFaceBodyService().compareFace(image, image);
        Assertions.assertTrue(result.isSuccess());
    }

}
