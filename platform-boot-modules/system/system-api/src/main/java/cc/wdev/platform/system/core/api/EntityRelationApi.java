package cc.wdev.platform.system.core.feign;

import cc.wdev.platform.system.core.domain.dto.CatalogRelationSaveDto;

/**
 * @author elvea
 */
public interface EntityRelationApi {

    void saveEntityRelation(CatalogRelationSaveDto saveDto);

}
