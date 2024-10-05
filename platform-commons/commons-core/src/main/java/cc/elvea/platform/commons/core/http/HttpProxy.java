package cc.elvea.platform.commons.core.http;

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
public class HttpProxy implements Serializable {

    @Builder.Default
    private boolean enabled = false;

    private String host;

    private Integer port;

    private String username;

    private String password;

}
