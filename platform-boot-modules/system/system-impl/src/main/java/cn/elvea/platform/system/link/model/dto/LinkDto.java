package cn.elvea.platform.system.link.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class LinkDto implements Serializable {
    private String larkUrl;
    private String larkWebappUrl;
    private String larkMobileUrl;

    private String dingtalkUrl;
    private String dingtalkWebappUrl;
    private String dingtalkMobileUrl;

    private String wecomUrl;
    private String wecomWebappUrl;
    private String wecomMobileUrl;
}
