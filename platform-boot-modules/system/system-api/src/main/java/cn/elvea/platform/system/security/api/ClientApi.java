package cn.elvea.platform.system.security.api;

import cn.elvea.platform.system.security.model.dto.ClientDto;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface ClientApi {

    void save(ClientDto clientDto);

    ClientDto findById(Long id);

    ClientDto findByClientId(String clientId);

}
