package cn.elvea.platform.commons.core.oapis.weixin.config;

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
public class AppCpConfig implements Serializable {

    private String corpId;

    private String corpSecret;

    private Integer agentId;

    private String token;

}
