package cc.elvea.platform.system.catalog.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * CatalogTypeDto
 *
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
public class CatalogTypeDto implements Serializable {
    /**
     * 编号
     */
    private String code;
    /**
     * 文本
     */
    private String label;
    /**
     * 备注
     */
    private String description;
}
