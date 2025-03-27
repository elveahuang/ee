package cc.elvea.platform.commons.oapis.facebody;

import cc.elvea.platform.commons.oapis.facebody.aliyun.AliyunFaceBodyService;
import cc.elvea.platform.commons.oapis.facebody.enums.FaceBodyTypeEnum;
import cc.elvea.platform.commons.oapis.facebody.tencent.TencentFaceBodyService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaceBodyConfig {
    @Builder.Default
    private Boolean enabled = Boolean.FALSE;
    @Builder.Default
    private FaceBodyTypeEnum type = FaceBodyTypeEnum.Aliyun;
    @Builder.Default
    private AliyunFaceBodyService.Config aliyun = new AliyunFaceBodyService.Config();
    @Builder.Default
    private TencentFaceBodyService.Config tencent = new TencentFaceBodyService.Config();
}
