package cc.wdev.platform.system.core.service.impl;

import cc.wdev.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.wdev.platform.commons.service.EntityService;
import cc.wdev.platform.system.core.domain.entity.BannerEntity;
import cc.wdev.platform.system.core.domain.form.BannerForm;
import cc.wdev.platform.system.core.domain.request.BannerSearchRequest;
import cc.wdev.platform.system.core.manager.AttachmentManager;
import cc.wdev.platform.system.core.manager.DictManager;
import cc.wdev.platform.system.core.repository.BannerRepository;
import cc.wdev.platform.system.core.service.BannerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author elvea
 */
@Slf4j
@Service
@AllArgsConstructor
public class BannerServiceImpl
    extends BaseCachingEntityService<BannerEntity, Long, BannerRepository>
    implements BannerService {

    private DictManager dictManager;

    private AttachmentManager attachmentManager;

    /**
     * @see BannerService#saveBanner(BannerForm)
     */
    @Override
    public void saveBanner(BannerForm form) {
    }

    /**
     * @see EntityService#deleteById(Serializable)
     */
    @Override
    public void deleteById(Long id) {
    }

    /**
     * @see BannerService#getExtra(BannerEntity)
     */
    @Override
    public void getExtra(BannerEntity banner) {

    }

    /**
     * @see BannerService#findBannerForUser(BannerSearchRequest)
     */
    @Override
    public Page<BannerEntity> findBannerForUser(@NonNull BannerSearchRequest searchRequest) {
        return null;
    }

    /**
     * @see BannerService#search(BannerSearchRequest)
     */
    @Override
    public Page<BannerEntity> search(BannerSearchRequest searchRequest) {
        return null;
    }

}
