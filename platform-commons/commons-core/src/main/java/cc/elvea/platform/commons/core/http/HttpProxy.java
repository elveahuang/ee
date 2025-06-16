package cc.elvea.platform.commons.core.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpProxy implements Serializable {

    private boolean enabled;

    private String host;

    private Integer port;

    private String username;

    private String password;

}
