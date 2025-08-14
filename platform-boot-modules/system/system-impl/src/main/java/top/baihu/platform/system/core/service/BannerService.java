package top.baihu.platform.system.core.service;

import org.springframework.data.domain.Page;
import top.baihu.platform.commons.service.CachingEntityService;
import top.baihu.platform.system.core.domain.entity.BannerEntity;
import top.baihu.platform.system.core.domain.form.BannerForm;
import top.baihu.platform.system.core.domain.request.BannerSearchRequest;

/**
 * @author elvea
 */
public interface BannerService extends CachingEntityService<BannerEntity, Long> {

    void saveBanner(BannerForm bannerForm);

    void getExtra(BannerEntity banner);

    Page<BannerEntity> findBannerForUser(BannerSearchRequest searchRequest);

    Page<BannerEntity> search(BannerSearchRequest searchRequest);

}
