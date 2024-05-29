package cc.elvea.platform.commons.oapis.weixin.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppCpConfig implements Serializable {

    private String corpId;

    private String corpSecret;

    private Integer agentId;

    private String token;

}
