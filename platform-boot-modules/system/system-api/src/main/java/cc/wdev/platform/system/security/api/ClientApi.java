package cc.wdev.platform.system.security.feign;

import cc.wdev.platform.system.security.domain.dto.ClientDto;

/**
 * @author elvea
 */
public interface ClientApi {

    void save(ClientDto clientDto);

    ClientDto findById(Long id);

    ClientDto findByClientId(String clientId);

}
