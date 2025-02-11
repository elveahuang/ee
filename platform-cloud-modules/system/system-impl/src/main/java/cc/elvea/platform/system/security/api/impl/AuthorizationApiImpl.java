package cc.elvea.platform.system.security.api.impl;

import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.security.api.AuthorizationApi;
import cc.elvea.platform.system.security.model.converter.AuthorizationConverter;
import cc.elvea.platform.system.security.model.dto.AuthorizationDto;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity;
import cc.elvea.platform.system.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elvea
 */
@AllArgsConstructor
@RestController
public class AuthorizationApiImpl implements AuthorizationApi {

    private final AuthorizationService authorizationService;

    /**
     * @see AuthorizationApi#save(AuthorizationDto)
     */
    @Override
    public R<Boolean> save(AuthorizationDto dto) {
        AuthorizationEntity entity = SpringUtils.getBean(AuthorizationConverter.class).dto2Entity(dto);
        this.authorizationService.save(entity);
        return R.success(Boolean.TRUE);
    }

    /**
     * @see AuthorizationApi#deleteById(Long)
     */
    @Override
    public R<Boolean> deleteById(Long id) {
        this.authorizationService.deleteById(id);
        return R.success(Boolean.TRUE);
    }

    /**
     * @see AuthorizationApi#findById(Long)
     */
    @Override
    public R<AuthorizationDto> findById(Long id) {
        AuthorizationEntity entity = this.authorizationService.findById(id);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationApi#findByState(String)
     */
    @Override
    public R<AuthorizationDto> findByState(String state) {
        AuthorizationEntity entity = this.authorizationService.findByState(state);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationApi#findByAuthorizationCodeValue(String)
     */
    @Override
    public R<AuthorizationDto> findByAuthorizationCodeValue(String code) {
        AuthorizationEntity entity = this.authorizationService.findByAuthorizationCodeValue(code);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationApi#findByOidcIdTokenValue(String)
     */
    @Override
    public R<AuthorizationDto> findByOidcIdTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByOidcIdTokenValue(token);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationApi#findByAccessTokenValue(String)
     */
    @Override
    public R<AuthorizationDto> findByAccessTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByAccessTokenValue(token);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

    /**
     * @see AuthorizationApi#findByRefreshTokenValue(String)
     */
    @Override
    public R<AuthorizationDto> findByRefreshTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByRefreshTokenValue(token);
        AuthorizationDto dto = SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
        return R.success(SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity));
    }

}
