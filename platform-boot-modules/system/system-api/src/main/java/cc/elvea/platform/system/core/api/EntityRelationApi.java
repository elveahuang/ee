package cc.elvea.platform.system.core.api;

import cc.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;

/**
 * @author elvea
 */
public interface EntityRelationApi {

    void saveEntityRelation(CatalogRelationSaveDto saveDto);

}
