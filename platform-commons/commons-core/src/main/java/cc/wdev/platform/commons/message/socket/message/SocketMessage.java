package cc.wdev.platform.commons.message.socket.message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
public class SocketMessage implements Serializable {
    protected String type;
    protected String content;
    protected String sessionId;
    protected String conversationId;
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long userId;
    protected String userType;
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long recipientId;
    protected String recipientType;
}
