package cc.wdev.platform.commons.core.tenant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantConfig implements Serializable {

    @Builder.Default
    private boolean enabled = false;

    @Builder.Default
    private long rootTenantId = 1L;

    @Builder.Default
    private List<String> excludes = Lists.newArrayList();

}
