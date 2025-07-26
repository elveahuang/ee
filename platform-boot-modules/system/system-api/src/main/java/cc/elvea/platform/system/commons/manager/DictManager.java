package cc.elvea.platform.system.commons.manager;

import cc.elvea.platform.system.commons.model.form.DictForm;
import cc.elvea.platform.system.commons.model.request.DictRelationRequest;
import cc.elvea.platform.system.commons.model.request.DictRelationSaveRequest;
import cc.elvea.platform.system.commons.model.request.DictSearchRequest;
import cc.elvea.platform.system.commons.model.request.DictTypeRequest;
import cc.elvea.platform.system.commons.model.vo.DictItemVo;
import cc.elvea.platform.system.commons.model.vo.DictRelationVo;
import cc.elvea.platform.system.commons.model.vo.DictTypeVo;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 */
public interface DictManager {

    /**
     * 获取字典类型
     */
    DictTypeVo getDictType(DictTypeRequest request);

    /**
     * 搜索字典
     */
    Page<DictItemVo> search(DictSearchRequest request);

    /**
     * 保存字典
     */
    void save(DictForm form);

    /**
     * 获取实体关联
     */
    DictRelationVo getRelation(DictRelationRequest request);

    /**
     * 保存实体关联
     */
    void saveRelation(DictRelationSaveRequest request);

}
