package cc.elvea.platform.commons.oapis.facebody;

import cc.elvea.platform.BaseTests;
import cc.elvea.platform.commons.oapis.facebody.model.FaceBodyResult;
import cn.hutool.core.codec.Base64;
import com.google.common.io.ByteStreams;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

/**
 * @author elvea
 */
@Slf4j
public class FaceBodyServiceTests extends BaseTests {

    @Autowired
    private FaceBodyFactory faceBodyManager;

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
