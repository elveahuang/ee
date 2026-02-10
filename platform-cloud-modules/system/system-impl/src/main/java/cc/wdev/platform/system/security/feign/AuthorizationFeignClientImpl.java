package cc.wdev.platform.system.security.feign;

import cc.wdev.platform.commons.domain.R;
import cc.wdev.platform.commons.utils.SpringUtils;
import cc.wdev.platform.system.security.domain.converter.AuthorizationConverter;
import cc.wdev.platform.system.security.domain.dto.AuthorizationDto;
import cc.wdev.platform.system.security.domain.entity.AuthorizationEntity;
import cc.wdev.platform.system.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
public class AuthorizationFeignClientImpl implements AuthorizationFeignClient {

    private final AuthorizationService authorizationService;

    /**
     * @see AuthorizationFeignClient#save(AuthorizationDto)
     */
    @Override
    public R<Boolean> save(AuthorizationDto dto) {
        AuthorizationEntity entity = SpringUtils.getBean(AuthorizationConverter.class).dto2Entity(dto);
        this.authorizationService.save(entity);
        return R.success(Boolean.TRUE);
    }

    /**
     * @see AuthorizationFeignClient#deleteById(Long)
     */
    @Override
    public R<Boolean> deleteById(Long id) {
        this.authorizationService.deleteById(id);
        return R.success(Boolean.TRUE);
    }

    /**
     * @see AuthorizationFeignClient#findById(Long)
     */
    @Override
    public R<AuthorizationDto> findById(Long id) {
        AuthorizationEntity entity = this.authorizationService.findById(id);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationFeignClient#findByState(String)
     */
    @Override
    public R<AuthorizationDto> findByState(String state) {
        AuthorizationEntity entity = this.authorizationService.findByState(state);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationFeignClient#findByAuthorizationCodeValue(String)
     */
    @Override
    public R<AuthorizationDto> findByAuthorizationCodeValue(String code) {
        AuthorizationEntity entity = this.authorizationService.findByAuthorizationCodeValue(code);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationFeignClient#findByOidcIdTokenValue(String)
     */
    @Override
    public R<AuthorizationDto> findByOidcIdTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByOidcIdTokenValue(token);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationFeignClient#findByAccessTokenValue(String)
     */
    @Override
    public R<AuthorizationDto> findByAccessTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByAccessTokenValue(token);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationFeignClient#findByRefreshTokenValue(String)
     */
    @Override
    public R<AuthorizationDto> findByRefreshTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByRefreshTokenValue(token);
        AuthorizationDto dto = SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

}
