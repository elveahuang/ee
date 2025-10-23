package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author elvea
 */
@Getter
@AllArgsConstructor
public enum SslProtocolTypeEnum implements BaseBizTypeEnum<String> {
    SSL("SSL", "SSL"),
    STARTTLS("STARTTLS", "STARTTLS");

    private final String value;
    private final String description;

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.SSL_PROTOCOL_TYPE.getValue().toUpperCase();
    }

}
