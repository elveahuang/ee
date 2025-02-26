package cc.elvea.platform.commons.oapis.facebody.tencent;

import cc.elvea.platform.commons.oapis.facebody.FaceBodyService;
import cc.elvea.platform.commons.oapis.facebody.model.FaceBodyResult;
import cc.elvea.platform.commons.utils.JacksonUtils;
import com.aliyun.tea.TeaModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.iai.v20200303.IaiClient;
import com.tencentcloudapi.iai.v20200303.models.CompareFaceRequest;
import com.tencentcloudapi.iai.v20200303.models.CompareFaceResponse;
import com.tencentcloudapi.iai.v20200303.models.DetectLiveFaceRequest;
import com.tencentcloudapi.iai.v20200303.models.DetectLiveFaceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 当前阿里云视觉智能开放平台只支持上海区
 *
 * @author elvea
 */
@Slf4j
public class TencentFaceBodyService implements FaceBodyService {

    private final Config config;

    public TencentFaceBodyService(Config config) {
        this.config = config;
    }

    @Override
    public FaceBodyResult detectFace(String image) {
        try {
            DetectLiveFaceRequest request = new DetectLiveFaceRequest();
            DetectLiveFaceResponse response = getClient().DetectLiveFace(request);

            String responseJson = JacksonUtils.toJson(TeaModel.toMap(response));
            log.info("TencentFaceBodyService.detectFace response - {}.", responseJson);

            return FaceBodyResult.builder()
                    .success(response.getScore() > 0)
                    .image(image)
                    .resp(responseJson).build();
        } catch (Exception e) {
            log.error("TencentFaceBodyService.detectFace failed.", e);
        }
        return FaceBodyResult.builder().success(false).build();
    }

    @Override
    public FaceBodyResult compareFace(String target, String source) {
        try {
            CompareFaceRequest request = new CompareFaceRequest();
            CompareFaceResponse response = getClient().CompareFace(request);

            String responseJson = JacksonUtils.toJson(TeaModel.toMap(response));
            log.info("TencentFaceBodyService.compareFace response - {}.", responseJson);

            return FaceBodyResult.builder()
                    .success(response.getScore() > 0)
                    .source(source)
                    .target(target)
                    .resp(responseJson).build();
        } catch (Exception e) {
            log.error("TencentFaceBodyService.detectFace failed.", e);
        }
        return FaceBodyResult.builder().success(false).build();
    }

    public IaiClient getClient() {
        Credential credential = new Credential(this.config.getSecretId(), this.config.getSecretKey());
        return new IaiClient(credential, this.config.getRegion());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config implements Serializable {
        @Builder.Default
        private Boolean enabled = Boolean.FALSE;
        @Builder.Default
        private String endpoint = "iai.tencentcloudapi.com";
        @Builder.Default
        private String region = "";
        @Builder.Default
        private String secretId = "";
        @Builder.Default
        private String secretKey = "";
    }

}
