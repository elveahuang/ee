package cc.elvea.platform.commons.extensions.ip;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoLiteConfig implements Serializable {

    @Builder.Default
    private LocationEnum location = LocationEnum.FILE_SYSTEM;

    @Builder.Default
    private String path = "";

}
