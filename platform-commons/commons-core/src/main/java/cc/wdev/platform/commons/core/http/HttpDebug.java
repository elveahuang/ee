package cc.wdev.platform.commons.core.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpDebug implements Serializable {

    @Builder.Default
    private boolean enabled = false;

}
