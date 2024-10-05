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
public class HttpConfig implements Serializable {

    @Builder.Default
    HttpDebug debug = HttpDebug.builder().build();

    @Builder.Default
    HttpType type = HttpType.OKHTTP;

    @Builder.Default
    HttpProxy proxy = HttpProxy.builder().build();

}
