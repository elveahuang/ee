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
public class AppMpConfig implements Serializable {

    private String appId;

    private String appSecret;

}
