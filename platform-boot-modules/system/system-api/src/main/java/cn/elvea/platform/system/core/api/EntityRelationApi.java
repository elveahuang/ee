package cn.elvea.platform.system.core.api;

import cn.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface EntityRelationApi {

    void saveEntityRelation(CatalogRelationSaveDto saveDto);

}
