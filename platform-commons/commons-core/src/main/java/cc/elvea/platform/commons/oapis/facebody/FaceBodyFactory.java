package cc.elvea.platform.commons.oapis.facebody;

import cc.elvea.platform.commons.oapis.facebody.aliyun.AliyunFaceBodyService;
import cc.elvea.platform.commons.oapis.facebody.enums.FaceBodyTypeEnum;
import cc.elvea.platform.commons.oapis.facebody.tencent.TencentFaceBodyService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author elvea
 */
@Slf4j
public record FaceBodyFactory(FaceBodyConfig config) {

    public FaceBodyService getFaceBodyService() {
        if (FaceBodyTypeEnum.Tencent.equals(config.getType())) {
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
