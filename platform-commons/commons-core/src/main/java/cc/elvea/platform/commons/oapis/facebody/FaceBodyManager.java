package cc.elvea.platform.commons.oapis.facebody;

import cc.elvea.platform.commons.oapis.facebody.aliyun.AliyunFaceBodyService;
import cc.elvea.platform.commons.oapis.facebody.tencent.TencentFaceBodyService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class FaceBodyManager {

    private final FaceBodyConfig config;

    public FaceBodyManager(FaceBodyConfig config) {
        this.config = config;
    }

    public FaceBodyService getFaceBodyService() {
        if (FaceBodyType.Aliyun.equals(config.getType())) {
            return getAliyunFaceBodyService();
        } else if (FaceBodyType.Tencent.equals(config.getType())) {
            return getTencentFaceBodyService();
        }
        return getAliyunFaceBodyService();
    }

    public FaceBodyService getAliyunFaceBodyService() {
        return getAliyunFaceBodyService(this.config.getAliyun());
    }

    public FaceBodyService getAliyunFaceBodyService(AliyunFaceBodyService.Config config) {
        return new AliyunFaceBodyService(config);
    }

    public FaceBodyService getTencentFaceBodyService() {
        return getTencentFaceBodyService(this.config.getTencent());
    }

    public FaceBodyService getTencentFaceBodyService(TencentFaceBodyService.Config config) {
        return new TencentFaceBodyService(config);
    }

}
