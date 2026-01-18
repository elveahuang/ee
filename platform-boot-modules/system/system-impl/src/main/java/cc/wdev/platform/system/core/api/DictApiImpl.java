package cc.wdev.platform.system.core.api;

import cc.wdev.platform.commons.utils.CollectionUtils;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.core.domain.converter.DictItemConverter;
import cc.wdev.platform.system.core.domain.entity.DictItemEntity;
import cc.wdev.platform.system.core.domain.form.DictForm;
import cc.wdev.platform.system.core.domain.request.DictRelationRequest;
import cc.wdev.platform.system.core.domain.request.DictRelationSaveRequest;
import cc.wdev.platform.system.core.domain.request.DictSearchRequest;
import cc.wdev.platform.system.core.domain.request.DictTypeRequest;
import cc.wdev.platform.system.core.domain.vo.DictItemVo;
import cc.wdev.platform.system.core.domain.vo.DictRelationVo;
import cc.wdev.platform.system.core.domain.vo.DictTypeVo;
import cc.wdev.platform.system.core.service.DictItemService;
import cc.wdev.platform.system.core.service.DictRelationService;
import cc.wdev.platform.system.core.service.DictTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictApiImpl implements DictApi {

    private final DictItemService dictItemService;

    private final DictTypeService dictTypeService;

    private final DictRelationService dictRelationService;

    /**
     * @see DictApi#getDictType(DictTypeRequest)
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
     * @see DictApi#search(DictSearchRequest)
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
     * @see DictApi#save(DictForm)
     */
    @Override
    public void save(DictForm form) {
        DictItemEntity entity = SpringUtils.getBean(DictItemConverter.class).form2Entity(form);
        this.dictItemService.save(entity);
    }

    /**
     * @see DictApi#getRelation(DictRelationRequest)
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
     * @see DictApi#saveRelation(DictRelationSaveRequest)
     */
    @Override
    public void saveRelation(DictRelationSaveRequest request) {
        this.dictRelationService.saveRelation(request);
    }

}
