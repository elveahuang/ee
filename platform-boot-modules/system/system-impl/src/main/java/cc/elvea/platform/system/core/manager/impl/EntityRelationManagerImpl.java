package cc.elvea.platform.system.core.manager.impl;

import cc.elvea.platform.system.catalog.model.dto.CatalogRelationSaveDto;
import cc.elvea.platform.system.core.manager.EntityRelationManager;
import cc.elvea.platform.system.core.service.EntityRelationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class EntityRelationManagerImpl implements EntityRelationManager {

    private final EntityRelationService entityRelationService;

    @Override
    public void saveEntityRelation(CatalogRelationSaveDto saveDto) {
    }

}
