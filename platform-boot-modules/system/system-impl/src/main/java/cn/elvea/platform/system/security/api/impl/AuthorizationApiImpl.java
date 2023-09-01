package cn.elvea.platform.system.security.api.impl;

import cn.elvea.platform.system.security.api.AuthorizationApi;
import cn.elvea.platform.system.security.model.converter.AuthorizationConverter;
import cn.elvea.platform.system.security.model.dto.AuthorizationDto;
import cn.elvea.platform.system.security.model.entity.AuthorizationEntity;
import cn.elvea.platform.system.security.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @since 0.0.1
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
        AuthorizationEntity entity = AuthorizationConverter.INSTANCE.dto2Entity(dto);
        this.authorizationService.save(entity);
    }

    /**
     * @see AuthorizationApi#deleteById(Long)
     */
    @Override
    public void deleteById(Long id) {
        this.authorizationService.deleteById(id);
    }

    /**
     * @see AuthorizationApi#findById(Long)
     */
    @Override
    public AuthorizationDto findById(Long id) {
        AuthorizationEntity entity = this.authorizationService.findById(id);
        return AuthorizationConverter.INSTANCE.entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByState(String)
     */
    @Override
    public AuthorizationDto findByState(String state) {
        AuthorizationEntity entity = this.authorizationService.findByState(state);
        return AuthorizationConverter.INSTANCE.entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByAuthorizationCodeValue(String)
     */
    @Override
    public AuthorizationDto findByAuthorizationCodeValue(String code) {
        AuthorizationEntity entity = this.authorizationService.findByAuthorizationCodeValue(code);
        return AuthorizationConverter.INSTANCE.entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByOidcIdTokenValue(String)
     */
    @Override
    public AuthorizationDto findByOidcIdTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByOidcIdTokenValue(token);
        return AuthorizationConverter.INSTANCE.entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByAccessTokenValue(String)
     */
    @Override
    public AuthorizationDto findByAccessTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByAccessTokenValue(token);
        return AuthorizationConverter.INSTANCE.entity2Dto(entity);
    }

    /**
     * @see AuthorizationApi#findByRefreshTokenValue(String)
     */
    @Override
    public AuthorizationDto findByRefreshTokenValue(String token) {
        AuthorizationEntity entity = this.authorizationService.findByRefreshTokenValue(token);
        return AuthorizationConverter.INSTANCE.entity2Dto(entity);
    }

}
