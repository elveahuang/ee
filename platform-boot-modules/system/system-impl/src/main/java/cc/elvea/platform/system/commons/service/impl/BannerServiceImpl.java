package cc.elvea.platform.system.commons.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.commons.manager.AttachmentManager;
import cc.elvea.platform.system.commons.manager.DictManager;
import cc.elvea.platform.system.commons.model.entity.BannerEntity;
import cc.elvea.platform.system.commons.model.form.BannerForm;
import cc.elvea.platform.system.commons.model.request.BannerSearchRequest;
import cc.elvea.platform.system.commons.repository.BannerRepository;
import cc.elvea.platform.system.commons.service.BannerService;
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
