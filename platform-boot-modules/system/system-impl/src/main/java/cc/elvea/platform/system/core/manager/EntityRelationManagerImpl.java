package cc.elvea.platform.system.core.manager;

import cc.elvea.platform.system.core.model.dto.CatalogRelationSaveDto;
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
