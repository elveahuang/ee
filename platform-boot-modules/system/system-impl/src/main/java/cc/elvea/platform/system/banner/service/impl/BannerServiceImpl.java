package cc.elvea.platform.system.banner.service.impl;

import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.service.EntityService;
import cc.elvea.platform.system.attachment.api.AttachmentApi;
import cc.elvea.platform.system.banner.model.entity.BannerEntity;
import cc.elvea.platform.system.banner.model.form.BannerForm;
import cc.elvea.platform.system.banner.model.request.BannerSearchRequest;
import cc.elvea.platform.system.banner.repository.BannerRepository;
import cc.elvea.platform.system.banner.service.BannerService;
import cc.elvea.platform.system.dict.api.DictApi;
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

    private DictApi dictApi;

    private AttachmentApi attachmentApi;

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
