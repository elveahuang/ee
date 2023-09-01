package cn.elvea.platform.system.catalog.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * CatalogTypeDto
 *
 * @author elvea
 * @since 0.0.1
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
