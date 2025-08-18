package cc.wdev.platform.system.core.manager;

import cc.wdev.platform.system.core.domain.dto.CatalogRelationSaveDto;
import cc.wdev.platform.system.core.service.EntityRelationService;
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
