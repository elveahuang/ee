package cc.wdev.platform.commons.core.tenant;

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
public class Tenant implements Serializable {

    public static final Tenant defaultTenant = Tenant.builder()
        .id(1000001L)
        .rootInd(1)
        .build();

    private Long id;

    private Integer rootInd;
}
