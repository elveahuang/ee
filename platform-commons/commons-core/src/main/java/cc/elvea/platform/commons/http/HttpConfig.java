package cc.elvea.platform.commons.http;

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
    HttpType type = HttpType.OKHTTP;

    @Builder.Default
    HttpProxy proxy = HttpProxy.builder().build();

}
