package cc.elvea.platform.system.commons.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@Schema(description = "应用初始设置")
public class InitializeVo implements Serializable {

    @Schema(description = "是否启用登录验证码")
    private boolean loginCaptchaEnabled;

}
