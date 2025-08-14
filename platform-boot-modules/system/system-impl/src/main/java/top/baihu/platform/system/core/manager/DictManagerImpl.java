package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.core.domain.converter.DictItemConverter;
import top.baihu.platform.system.core.domain.entity.DictItemEntity;
import top.baihu.platform.system.core.domain.form.DictForm;
import top.baihu.platform.system.core.domain.request.DictRelationRequest;
import top.baihu.platform.system.core.domain.request.DictRelationSaveRequest;
import top.baihu.platform.system.core.domain.request.DictSearchRequest;
import top.baihu.platform.system.core.domain.request.DictTypeRequest;
import top.baihu.platform.system.core.domain.vo.DictItemVo;
import top.baihu.platform.system.core.domain.vo.DictRelationVo;
import top.baihu.platform.system.core.domain.vo.DictTypeVo;
import top.baihu.platform.system.core.service.DictItemService;
import top.baihu.platform.system.core.service.DictRelationService;
import top.baihu.platform.system.core.service.DictTypeService;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictManagerImpl implements DictManager {

    private final DictItemService dictItemService;

    private final DictTypeService dictTypeService;

    private final DictRelationService dictRelationService;

    /**
     * @see DictManager#getDictType(DictTypeRequest)
     */
    @Override
    public DictTypeVo getDictType(DictTypeRequest request) {
        DictTypeVo vo = dictTypeService.getDictType(request.getType());
        if (vo != null && request.isWithItem()) {
            List<DictItemEntity> dictItemList = this.dictItemService.findByTypeId(vo.getId());
            if (CollectionUtils.isNotEmpty(dictItemList)) {
                vo.setItems(dictItemList.stream().map(SpringUtils.getBean(DictItemConverter.class)::entity2Vo).toList());
            }
        }
        return vo;
    }

    /**
     * @see DictManager#search(DictSearchRequest)
     */
    @Override
    public Page<DictItemVo> search(DictSearchRequest request) {
        // 指定类型ID
        request.setTypeId(dictTypeService.getDictTypeId(request.getType()));

        Page<DictItemEntity> page = this.dictItemService.search(request);
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<DictItemVo> itemList = page.getContent().stream().map(SpringUtils.getBean(DictItemConverter.class)::entity2Vo).toList();
            return new PageImpl<>(itemList, request.getPageable(), page.getTotalElements());
        }
        return new PageImpl<>(Collections.emptyList(), request.getPageable(), 0);
    }

    /**
     * @see DictManager#save(DictForm)
     */
    @Override
    public void save(DictForm form) {
        DictItemEntity entity = SpringUtils.getBean(DictItemConverter.class).form2Entity(form);
        this.dictItemService.save(entity);
    }

    /**
     * @see DictManager#getRelation(DictRelationRequest)
     */
    @Override
    public DictRelationVo getRelation(DictRelationRequest request) {
        DictRelationVo vo = DictRelationVo.builder().targetType(request.getTargetType()).targetId(request.getTargetId()).build();

        List<DictItemEntity> tagList = this.dictItemService.findByTarget(request);
        if (CollectionUtils.isNotEmpty(tagList)) {
            vo.setItems(tagList.stream().map(SpringUtils.getBean(DictItemConverter.class)::entity2Vo).toList());
        }
        return vo;
    }

    /**
     * @see DictManager#saveRelation(DictRelationSaveRequest)
     */
    @Override
    public void saveRelation(DictRelationSaveRequest request) {
        this.dictRelationService.saveRelation(request);
    }

}
