package cc.wdev.platform.system.message.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseEntityService;
import cc.wdev.platform.commons.utils.SecurityUtils;
import cc.wdev.platform.system.message.domain.entity.NoticeEntity;
import cc.wdev.platform.system.message.domain.entity.NoticeEntity_;
import cc.wdev.platform.system.message.domain.request.NoticeSearchRequest;
import cc.wdev.platform.system.message.repository.NoticeRepository;
import cc.wdev.platform.system.message.service.NoticeService;
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
