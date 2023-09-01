package cn.elvea.platform.commons.core.extensions.mail;

import cn.elvea.platform.commons.core.enums.SslProtocolTypeEnum;
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
public class MailServer implements Serializable {

    @Builder.Default
    private Boolean enabled = Boolean.TRUE;

    @Builder.Default
    private Boolean auth = Boolean.TRUE;

    @Builder.Default
    private Boolean ssl = Boolean.TRUE;

    @Builder.Default
    private SslProtocolTypeEnum sslProtocol = SslProtocolTypeEnum.SSL;

    private String from;

    private String host;

    private int port;

    private String username;

    private String password;

}
