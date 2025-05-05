package cc.elvea.platform.system.security.api.impl;

import cc.elvea.platform.commons.utils.SpringUtils;
import cc.elvea.platform.system.security.api.AuthorizationApi;
import cc.elvea.platform.system.security.model.converter.AuthorizationConverter;
import cc.elvea.platform.system.security.model.dto.AuthorizationDto;
import cc.elvea.platform.system.security.model.entity.AuthorizationEntity;
import cc.elvea.platform.system.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 */
@Service
@AllArgsConstructor
public class AuthorizationApiImpl implements AuthorizationApi {

    private final AuthorizationService authorizationService;

    /**
     * @see AuthorizationApi#save(AuthorizationDto)
     */
    @Override
    public void save(AuthorizationDto dto) {
        AuthorizationEntity entity = SpringUtils.getBean(AuthorizationConverter.class).dto2Entity(dto);
        this.authorizationService.updateByUuid(entity);
    }

    /**
     * @see AuthorizationApi#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        this.authorizationService.deleteById(id);
    }

    /**
     * @see AuthorizationApi#deleteByUuid(String)
     */
    @Override
    public void deleteByUuid(String uuid) {
        this.authorizationService.deleteByUuid(uuid);
    }

    /**
     * @see AuthorizationApi#findById(Long)
     */
    @Override
    public AuthorizationDto findById(Long id) {
        AuthorizationEntity entity = this.authorizationService.findById(id);
        return SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByUuid(String)
     */
    @Override
    public AuthorizationDto findByUuid(String uuid) {
        AuthorizationEntity entity = this.authorizationService.findByUuid(uuid);
        return SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByState(String)
     */
    @Override
    public AuthorizationDto findByState(String state) {
        AuthorizationEntity entity = this.authorizationService.findByState(state);
        return SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByAuthorizationCodeValue(String)
     */
    @Override
    public AuthorizationDto findByAuthorizationCodeValue(String code) {
        AuthorizationEntity entity = this.authorizationService.findByAuthorizationCodeValue(code);
        return SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByOidcIdTokenValue(String)
     */
    @Override
    public AuthorizationDto findByOidcIdTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByOidcIdTokenValue(token);
        return SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByAccessTokenValue(String)
     */
    @Override
    public AuthorizationDto findByAccessTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByAccessTokenValue(token);
        return SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByRefreshTokenValue(String)
     */
    @Override
    public AuthorizationDto findByRefreshTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByRefreshTokenValue(token);
        return SpringUtils.getBean(AuthorizationConverter.class).entity2Dto(entity);
    }

}
