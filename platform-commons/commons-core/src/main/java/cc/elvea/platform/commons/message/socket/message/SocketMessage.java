package cc.elvea.platform.commons.message.socket.message;

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
}
