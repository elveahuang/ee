package cc.elvea.platform.system.message.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseEntityService;
import cc.elvea.platform.commons.utils.SecurityUtils;
import cc.elvea.platform.system.message.model.entity.NoticeEntity;
import cc.elvea.platform.system.message.model.entity.NoticeEntity_;
import cc.elvea.platform.system.message.repository.NoticeRepository;
import cc.elvea.platform.system.message.request.NoticeSearchRequest;
import cc.elvea.platform.system.message.service.NoticeService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class NoticeServiceImpl
        extends BaseEntityService<NoticeEntity, Long, NoticeRepository>
        implements NoticeService {

    /**
     * @see NoticeService#findMyNoticeByUserId(NoticeSearchRequest)
     */
    @Override
    public Page<NoticeEntity> findMyNoticeByUserId(NoticeSearchRequest request) {
        request.setUserId(SecurityUtils.getUid());
        Specification<NoticeEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = org.apache.commons.compress.utils.Lists.newArrayList();
            predicates.add(builder.equal(root.get(NoticeEntity_.RECIPIENT_ID), request.getUserId()));
            predicates.add(builder.equal(root.get(NoticeEntity_.ACTIVE), Boolean.TRUE));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification, request.getPageable());
    }

    @Override
    public Page<NoticeEntity> findByUserId(NoticeSearchRequest request) {
        Specification<NoticeEntity> specification = (root, query, builder) -> {
            List<Predicate> predicates = org.apache.commons.compress.utils.Lists.newArrayList();
            if (request.getUserId() != null && request.getUserId() > 0) {
                predicates.add(builder.equal(root.get(NoticeEntity_.RECIPIENT_ID), request.getUserId()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
        return this.repository.findAll(specification, request.getPageable());
    }

}
