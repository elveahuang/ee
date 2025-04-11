package cc.elvea.platform.system.dict.api;

import cc.elvea.platform.system.dict.model.form.DictForm;
import cc.elvea.platform.system.dict.model.request.DictRelationRequest;
import cc.elvea.platform.system.dict.model.request.DictRelationSaveRequest;
import cc.elvea.platform.system.dict.model.request.DictSearchRequest;
import cc.elvea.platform.system.dict.model.request.DictTypeRequest;
import cc.elvea.platform.system.dict.model.vo.DictItemVo;
import cc.elvea.platform.system.dict.model.vo.DictRelationVo;
import cc.elvea.platform.system.dict.model.vo.DictTypeVo;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 */
public interface DictApi {

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
