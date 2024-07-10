package cc.elvea.platform.commons.mail;

import cc.elvea.platform.commons.enums.SslProtocolTypeEnum;
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
public class MailServer implements Serializable {
    @Builder.Default
    private boolean enabled = false;
    @Builder.Default
    private boolean auth = true;
    @Builder.Default
    private boolean ssl = true;
    @Builder.Default
    private SslProtocolTypeEnum sslProtocol = SslProtocolTypeEnum.SSL;
    private String from;
    private String host;
    private int port;
    private String username;
    private String password;
}
