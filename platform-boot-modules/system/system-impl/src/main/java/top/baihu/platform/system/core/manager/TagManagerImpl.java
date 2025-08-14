package top.baihu.platform.system.core.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.baihu.platform.commons.utils.CollectionUtils;
import top.baihu.platform.commons.utils.SpringUtils;
import top.baihu.platform.system.core.domain.converter.TagConverter;
import top.baihu.platform.system.core.domain.entity.TagEntity;
import top.baihu.platform.system.core.domain.form.TagForm;
import top.baihu.platform.system.core.domain.request.TagRelationRequest;
import top.baihu.platform.system.core.domain.request.TagRelationSaveRequest;
import top.baihu.platform.system.core.domain.request.TagSearchRequest;
import top.baihu.platform.system.core.domain.request.TagTypeRequest;
import top.baihu.platform.system.core.domain.vo.TagRelationVo;
import top.baihu.platform.system.core.domain.vo.TagTypeVo;
import top.baihu.platform.system.core.domain.vo.TagVo;
import top.baihu.platform.system.core.service.TagRelationService;
import top.baihu.platform.system.core.service.TagService;
import top.baihu.platform.system.core.service.TagTypeService;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class TagManagerImpl implements TagManager {

    private final TagService tagService;

    private final TagTypeService tagTypeService;

    private final TagRelationService tagRelationService;

    /**
     * @see TagManager#getTagType(TagTypeRequest)
     */
    @Override
    public TagTypeVo getTagType(TagTypeRequest request) {
        TagTypeVo vo = tagTypeService.getTagType(request.getType());
        if (vo != null && request.isWithItem()) {
            List<TagEntity> tagList = this.tagService.findByTypeId(vo.getId());
            if (CollectionUtils.isNotEmpty(tagList)) {
                vo.setItems(tagList.stream().map(SpringUtils.getBean(TagConverter.class)::entity2Vo).toList());
            }
        }
        return vo;
    }

    /**
     * @see TagManager#search(TagSearchRequest)
     */
    @Override
    public Page<TagVo> search(TagSearchRequest request) {
        // 指定类型ID
        request.setTypeId(tagTypeService.getTagTypeId(request.getType()));

        Page<TagEntity> page = this.tagService.search(request);
        if (CollectionUtils.isNotEmpty(page.getContent())) {
            List<TagVo> itemList = page.getContent().stream().map(SpringUtils.getBean(TagConverter.class)::entity2Vo).toList();
            return new PageImpl<>(itemList, request.getPageable(), page.getTotalElements());
        }
        return new PageImpl<>(Collections.emptyList(), request.getPageable(), 0);
    }

    /**
     * @see TagManager#save(TagForm)
     */
    @Override
    public void save(TagForm form) {
        TagEntity entity = SpringUtils.getBean(TagConverter.class).form2Entity(form);
        this.tagService.save(entity);
    }

    /**
     * @see TagManager#getRelation(TagRelationRequest)
     */
    @Override
    public TagRelationVo getRelation(TagRelationRequest request) {
        TagRelationVo vo = TagRelationVo.builder().targetType(request.getTargetType()).targetId(request.getTargetId()).build();

        List<TagEntity> tagList = this.tagService.findByTarget(request);
        if (CollectionUtils.isNotEmpty(tagList)) {
            vo.setItems(tagList.stream().map(SpringUtils.getBean(TagConverter.class)::entity2Vo).toList());
        }
        return vo;
    }

    /**
     * @see TagManager#saveRelation(TagRelationSaveRequest)
     */
    @Override
    public void saveRelation(TagRelationSaveRequest request) {
        this.tagRelationService.saveRelation(request);
    }

}
