package cc.elvea.platform.system.core.service;

import cc.elvea.platform.commons.service.CachingEntityService;
import cc.elvea.platform.system.core.domain.entity.BannerEntity;
import cc.elvea.platform.system.core.domain.form.BannerForm;
import cc.elvea.platform.system.core.domain.request.BannerSearchRequest;
import org.springframework.data.domain.Page;

/**
 * @author elvea
 */
public interface BannerService extends CachingEntityService<BannerEntity, Long> {

    void saveBanner(BannerForm bannerForm);

    void getExtra(BannerEntity banner);

    Page<BannerEntity> findBannerForUser(BannerSearchRequest searchRequest);

    Page<BannerEntity> search(BannerSearchRequest searchRequest);

}
