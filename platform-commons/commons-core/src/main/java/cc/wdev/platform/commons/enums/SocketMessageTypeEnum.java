package cc.wdev.platform.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocketMessageTypeEnum implements BaseBizTypeEnum<String> {


    PING("ping", "PING"),
    VALID("valid", "校验"),
    ACK("ack", "确认"),
    CHAT("chat", "沟通"),
    ;

    private final String type;

    private final String description;

    @Override
    public String getValue() {
        return this.type;
    }

    @Override
    public String getGroup() {
        return BizGroupTypeEnum.SOCKET_MESSAGE_TYPE.getValue();
    }
}
