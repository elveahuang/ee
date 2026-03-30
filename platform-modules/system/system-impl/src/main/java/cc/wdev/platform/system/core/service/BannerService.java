package cc.wdev.platform.system.core.service;

import cc.wdev.platform.commons.service.CachingEntityService;
import cc.wdev.platform.system.core.domain.entity.BannerEntity;
import cc.wdev.platform.system.core.domain.form.BannerForm;
import cc.wdev.platform.system.core.domain.request.BannerSearchRequest;
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
