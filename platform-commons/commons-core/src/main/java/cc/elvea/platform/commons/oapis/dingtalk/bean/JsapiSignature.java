package cc.elvea.platform.commons.oapis.dingtalk.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsapiSignature implements Serializable {

    private String agentId;

    private String corpId;

    private String nonceStr;

    private long timestamp;

    private String url;

    private String signature;

}
