package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.system.core.domain.entity.SubjectEntity;
import cc.wdev.platform.system.core.repository.SubjectRepository;
import cc.wdev.platform.system.core.service.SubjectService;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
public class SubjectServiceImpl extends BaseCachingEntityService<SubjectEntity, Long, SubjectRepository> implements SubjectService {
}
