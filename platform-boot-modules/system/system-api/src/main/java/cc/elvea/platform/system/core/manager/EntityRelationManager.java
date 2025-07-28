package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.system.core.domain.dto.CatalogRelationSaveDto;

/**
 * @author elvea
 */
public interface EntityRelationManager {

    void saveEntityRelation(CatalogRelationSaveDto saveDto);

}
