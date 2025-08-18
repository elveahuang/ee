package cc.wdev.platform.system.core.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
public class Operator implements Serializable {
    private Long id;
    private String username;
    private String displayName;
}
