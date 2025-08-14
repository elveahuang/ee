package top.baihu.platform.commons.core.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.baihu.platform.commons.enums.SslProtocolTypeEnum;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailConfig implements Serializable {

    @Builder.Default
    private boolean auth = true;

    @Builder.Default
    private boolean ssl = true;

    @Builder.Default
    private SslProtocolTypeEnum sslProtocol = SslProtocolTypeEnum.SSL;

    private String from;

    private String host;

    private int port;

    private String name;

    private String username;

    private String password;

}
