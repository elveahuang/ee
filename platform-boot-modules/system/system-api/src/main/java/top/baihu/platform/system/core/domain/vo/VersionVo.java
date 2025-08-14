package top.baihu.platform.system.core.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import top.baihu.platform.commons.constants.GlobalConstants;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@Schema(description = "应用初始设置")
public class VersionVo implements Serializable {

    @Builder.Default
    @Schema(description = "版本号")
    private String version = GlobalConstants.VERSION;

}
