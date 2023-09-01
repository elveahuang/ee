package cn.elvea.platform.commons.core.oapis.lark.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "飞书鉴权信息")
public class JsapiSignature implements Serializable {

    @Schema(name = "飞书应用编号")
    private String appId;

    @Schema(name = "随机字符串")
    private String nonceStr;

    @Schema(name = "当前时间戳")
    private long timestamp;

    @Schema(name = "当前网页地址")
    private String url;

    @Schema(name = "签名")
    private String signature;

}
