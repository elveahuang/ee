package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.baihu.platform.system.core.domain.dto.CatalogRelationSaveDto;
import top.baihu.platform.system.core.service.EntityRelationService;

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
