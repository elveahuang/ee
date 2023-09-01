package cn.elvea.platform.system.commons.model.vo;

import cn.elvea.platform.commons.core.annotations.JsonFormat;
import cn.elvea.platform.commons.core.constants.DateTimeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@Schema(description = "版本信息")
public class InitializeVo implements Serializable {

    @Schema(description = "版本号")
    private String version;

    @Schema(description = "当前系统时间")
    @JsonFormat(pattern = DateTimeConstants.DEFAULT_DATE_TIME_PATTERN)
    private LocalDateTime now;

}
