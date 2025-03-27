package cc.elvea.platform.system.core.api.impl;

import cc.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;
import cc.elvea.platform.system.core.api.EntityRelationApi;
import cc.elvea.platform.system.core.service.EntityRelationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class EntityRelationApiImpl implements EntityRelationApi {

    private final EntityRelationService entityRelationService;

    @Override
    public void saveEntityRelation(CatalogRelationSaveDto saveDto) {
    }

}
