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
    private String type;
    private String content;
    private String sessionId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
}
