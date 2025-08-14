package top.baihu.platform.system.core.manager;

import org.springframework.data.domain.Page;
import top.baihu.platform.system.core.domain.form.DictForm;
import top.baihu.platform.system.core.domain.request.DictRelationRequest;
import top.baihu.platform.system.core.domain.request.DictRelationSaveRequest;
import top.baihu.platform.system.core.domain.request.DictSearchRequest;
import top.baihu.platform.system.core.domain.request.DictTypeRequest;
import top.baihu.platform.system.core.domain.vo.DictItemVo;
import top.baihu.platform.system.core.domain.vo.DictRelationVo;
import top.baihu.platform.system.core.domain.vo.DictTypeVo;

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
